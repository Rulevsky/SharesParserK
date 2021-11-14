package com.example.sharesparserk


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.os.PowerManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.sharesparserk.Common.Common
import com.example.sharesparserk.Interface.RetrofitServices
import com.example.sharesparserk.database1.SSDatabase
import com.example.sharesparserk.database1.StocksSettings
import com.example.sharesparserk.model.AllStocks
import com.example.sharesparserk.model.OneStockPosition
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriceCheckService : Service() {
    var isRunning:Boolean = false
    var CHANNEL_ID = "price_notf_channed_id"
    var NOTIFICATION_ID = 101

    val applicationScope = CoroutineScope(SupervisorJob())


    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
        Log.e("PriceCheckService", "onbind")
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("PriceCheckService", "servis sozdan")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        powerManagment()
        isRunning = true
        //Was global scope, memory leak.
        CoroutineScope(SupervisorJob()).launch(Dispatchers.IO) {
            repeat(100_000) {
                delay(10000L)
                Log.e("PriceCheckService", " global scope coroutine")
                try {
                    fetchData()
                } catch (e: Exception) {
                    Log.e("PriceCheckService","fetchdata exception: " + e.toString())
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



    suspend fun fetchData() {
        Log.e("PriceCheckService", "fetchdata started")
        var mService: RetrofitServices = Common.retrofitService
        var dataSet: MutableList<OneStockPosition> = mutableListOf()
        mService.getSharesList().enqueue(object : Callback<AllStocks> {
            override fun onResponse(
                call: Call<AllStocks>,
                response: Response<AllStocks>
            ) {
                var stocksData = response.body()!!
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

                val stocksSettingsDatabase =
                    SSDatabase.getInstance(applicationContext, applicationScope).ssDatabaseDao()

                CoroutineScope(SupervisorJob()).launch(Dispatchers.IO) {
                    var i: Int = 0
                    while (i <= 31) {
                        var currentStockSetting: StocksSettings? = stocksSettingsDatabase.get(i+1)
                        currentStockSetting = StocksSettings(
                            currentStockSetting!!.settingsID,
                            currentStockSetting.lowPrice,
                            currentStockSetting.highPrice,
                            dataSet[i].acronym,
                            dataSet[i].currentPrice
                        )
                        stocksSettingsDatabase.update(currentStockSetting)
                        i++
                    }
                }

                // use CoroutineScope(SupervisorJob()) if memory leak
                GlobalScope.launch(Dispatchers.IO) {
                var i: Int = 1
                while (i <= 32) {
                    var currentPrice = stocksSettingsDatabase.get(i)?.currentPrice
                    if (currentPrice != null) {
                        if (currentPrice < stocksSettingsDatabase.get(i)!!.lowPrice || currentPrice > stocksSettingsDatabase.get(
                                i
                            )!!.highPrice
                        ) {
                            createNotificationChannel()
                            var builder = notification()
                            with(NotificationManagerCompat.from(applicationContext)) {
                                notify(NOTIFICATION_ID, builder.build())
                                Log.e(
                                    "price check service",
                                    "PRICE NOTIFICATION!!!:" + stocksSettingsDatabase.get(i)?.acronym
                                )
                            }
                        }

                        i++

                       }

                    }
                    Log.e("PriceCheckService", "fetch and check done")
                }
            }

            override fun onFailure(call: Call<AllStocks>, t: Throwable) {
                Log.e("tag", "failure: " + t.toString())
            }
        })


    }
    // <uses-permission android:name="android.permission.WAKE_LOCK" />
    //dont work

    fun powerManagment(){
     val wakeLock: PowerManager.WakeLock =
         (getSystemService(Context.POWER_SERVICE) as PowerManager).run{
            newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "SharesParserK::MyWakeLogTag").apply {
                acquire()
            }
        }
    }

}




