package com.example.sharesparserk


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.sharesparserk.Common.Common
import com.example.sharesparserk.Interface.RetrofitServices
import com.example.sharesparserk.database.StocksDatabase
import com.example.sharesparserk.model.AllStocks
import com.example.sharesparserk.model.Stocks
import kotlinx.coroutines.*
import kotlinx.coroutines.android.awaitFrame
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Runnable

import java.util.*

class PriceCheckService : Service() {
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
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

        Log.e("tag", "service on start command")


//        GlobalScope.launch {
//            repeat(1000){
//            delay(3000L)
//            checkIfSell()
//            }
//
//        }


        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()

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

    fun checkIfSell() {

        var mService: RetrofitServices = Common.retrofitService
        mService.getSharesList().enqueue(object : Callback<AllStocks> {
            override fun onResponse(
                call: Call<AllStocks>,
                response: Response<AllStocks>
            ) {
                var allStocks = response.body()!!
                Log.e("tag", "retrofit in service ok")
                Log.e("tag", allStocks.stocks.x16.currentPrice.toString())
                afterGetRetrofitResponse(allStocks)
            }

            override fun onFailure(call: Call<AllStocks>, t: Throwable) {
                Log.e("tag", "failure: " + t.toString())
            }
        })
    }


    fun afterGetRetrofitResponse(allStocks: AllStocks) {
        Log.e("tag", "afterGetRetrofitResponse")
        var sellingPrice = 720.00
        var currentPrice = allStocks.stocks.x16.currentPrice
        Log.e("tag", "current price is: " + currentPrice.toString())

        if (currentPrice >= sellingPrice) {
            createNotificationChannel()
            var builder = notification()
            with(NotificationManagerCompat.from(this)) {
                notify(NOTIFICATION_ID, builder.build())

            }
        } else Log.e("tag", " current price is else")
    }


}




