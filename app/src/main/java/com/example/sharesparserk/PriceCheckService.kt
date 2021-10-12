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
import androidx.core.app.NotificationManagerCompat.from

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
        mHandler = Handler()
        mRunnable = Runnable { showRandomNumber() }
        mHandler.postDelayed(mRunnable, 5000)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacks(mRunnable)
    }

    // Custom method to do a task
    private fun showRandomNumber() {
        val rand = Random()
        val number = rand.nextInt(100)
        Log.e("tag", "Random Number : $number" )
        if (number > 50){
            try {
                createNotificationChannel()
                var builder = NotificationCompat.Builder(this, CHANNEL_ID )
                    .setSmallIcon(R.drawable.ic_message)
                    .setContentTitle("Content Title")
                    .setContentText("Content Text")
                    .setStyle(NotificationCompat.BigTextStyle())
                    .setPriority(NotificationCompat.PRIORITY_HIGH)

                with(NotificationManagerCompat.from(this)) {
                    notify(NOTIFICATION_ID, builder.build())
                }
            } catch (e: Exception) {
                Log.e("tag", "notification catched: " + e.toString())
            }
        }
        mHandler.postDelayed(mRunnable, 5000)
    }

    fun notification(){
        var builder = NotificationCompat.Builder(this, CHANNEL_ID )
            .setContentTitle("Content Title")
            .setContentText("Content Text")
            .setStyle(NotificationCompat.BigTextStyle())
            .setPriority(NotificationCompat.PRIORITY_HIGH)


    }


    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
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



}