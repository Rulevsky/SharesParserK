package com.example.sharesparserk


import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.PowerManager
import android.util.Log
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sharesparserk.Common.Common
import com.example.sharesparserk.Interface.RetrofitServices
import com.example.sharesparserk.database.*
import com.example.sharesparserk.model.AllStocks
import com.example.sharesparserk.model.OneStockPosition
import com.example.sharesparserk.model.Stocks
import kotlinx.coroutines.*
import kotlinx.coroutines.android.awaitFrame
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Runnable

import java.util.*

class PriceCheckService : Service() {
    var isRunning:Boolean = false
    var CHANNEL_ID = "price_notf_channed_id"
    var NOTIFICATION_ID = 101


    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
        Log.e("tag", "onbind")
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("tag", "servis sozdan")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        isRunning = true
        GlobalScope.launch(Dispatchers.IO) {
            repeat(100_000) {
                delay(10000L)
                Log.e("tag", " global scope coroutine")
                try {
                    fetchData()
                } catch (e: Exception) {
                    Log.e("tag","fetchdata exception: " + e.toString())
                }
            }
        }


        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        createNotificationChannel()
        var builder = notification()
        with(NotificationManagerCompat.from(applicationContext)) {
            notify(NOTIFICATION_ID, builder.build())
        }
        isRunning = false

    }


    fun notification(): NotificationCompat.Builder {


        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_message)
            .setContentTitle("Content Title")
            .setContentText("Content Text")
            .setStyle(NotificationCompat.BigTextStyle())
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        return builder
    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

//    fun checkIfSell() {
//
//        var mService: RetrofitServices = Common.retrofitService
//        mService.getSharesList().enqueue(object : Callback<AllStocks> {
//            override fun onResponse(
//                call: Call<AllStocks>,
//                response: Response<AllStocks>
//            ) {
//                var allStocks = response.body()!!
//                Log.e("tag", "retrofit in service ok")
//                Log.e("tag", allStocks.stocks.x16.currentPrice.toString())
//
//            }
//
//            override fun onFailure(call: Call<AllStocks>, t: Throwable) {
//                Log.e("tag", "failure: " + t.toString())
//            }
//        })
//    }



    suspend fun fetchData() {
        Log.e("tag", "fetchdata started")
        var mService: RetrofitServices = Common.retrofitService
        var dataSet: MutableList<OneStockPosition> = mutableListOf()
        mService.getSharesList().enqueue(object : Callback<AllStocks> {
            override fun onResponse(
                call: Call<AllStocks>,
                response: Response<AllStocks>
            ) {
                var stocksData = response.body()!!
                Log.e("tag", "responsebody zapisalsa: " + stocksData.stocks.x1.toString())
                dataSet =
                    mutableListOf(
                        stocksData.stocks.x1, stocksData.stocks.x2, stocksData.stocks.x3,
                        stocksData.stocks.x4, stocksData.stocks.x5, stocksData.stocks.x6,
                        stocksData.stocks.x7, stocksData.stocks.x8, stocksData.stocks.x9,
                        stocksData.stocks.x10, stocksData.stocks.x11, stocksData.stocks.x12,
                        stocksData.stocks.x13, stocksData.stocks.x14, stocksData.stocks.x15,
                        stocksData.stocks.x16, stocksData.stocks.x17, stocksData.stocks.x18,
                        stocksData.stocks.x19, stocksData.stocks.x20, stocksData.stocks.x21,
                        stocksData.stocks.x22, stocksData.stocks.x23, stocksData.stocks.x24,
                        stocksData.stocks.x25, stocksData.stocks.x26, stocksData.stocks.x27,
                        stocksData.stocks.x28, stocksData.stocks.x29, stocksData.stocks.x30,
                        stocksData.stocks.x31, stocksData.stocks.x32
                    )
                val stocksDatabase =
                    StocksDatabase.getInstance(applicationContext).stocksDatabaseDao
                val settingsDatabase =
                    SettingsDatabase.getInstance(applicationContext).settingsDatabaseDao
                GlobalScope.launch(Dispatchers.IO) {
                var i: Int = 1
                while (i <= 32) {
                    var currentPrice = stocksDatabase.get(i)?.currentPrice
                    if (currentPrice != null) {
                        if (currentPrice < settingsDatabase.get(i)!!.lowPrice || currentPrice > settingsDatabase.get(
                                i
                            )!!.highPrice
                        ) {
                            createNotificationChannel()
                            var builder = notification()
                            with(NotificationManagerCompat.from(applicationContext)) {
                                notify(NOTIFICATION_ID, builder.build())
                                Log.e(
                                    "price check service",
                                    "PRICE NOTIFICATION!!!:" + stocksDatabase.get(i)?.acronym
                                )
                            }
                        }
                        Log.e("tag", "while:" + i.toString())
                        i++

                       }

                    }
                    Log.e("tag", "while ended")
                }
            }

            override fun onFailure(call: Call<AllStocks>, t: Throwable) {
                Log.e("tag", "failure: " + t.toString())
            }
        })


    }
    // <uses-permission android:name="android.permission.WAKE_LOCK"/>
    //dont work
    @SuppressLint("InvalidWakeLockTag")
    fun powerManagment(){
        var mgr: PowerManager = applicationContext.getSystemService(Context.POWER_SERVICE) as PowerManager
        var wakeLock = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "mywakelock")
        wakeLock.acquire();
    }

}




