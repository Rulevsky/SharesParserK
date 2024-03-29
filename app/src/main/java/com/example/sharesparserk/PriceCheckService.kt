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
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.sharesparserk.retrofit.Common.Common
import com.example.sharesparserk.retrofit.Interface.RetrofitServices
import com.example.sharesparserk.database1.SSDatabase
import com.example.sharesparserk.database1.StocksSettings
import com.example.sharesparserk.model.AllStocks
import com.example.sharesparserk.model.OneStockPosition
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriceCheckService : Service() {
    var isRunning: Boolean = false
    var CHANNEL_ID = "price_notf_channed_id"
    var NOTIFICATION_ID = 101
    val applicationScope = CoroutineScope(SupervisorJob())
    val APP_PREFERENCES_KEY = "apikey"
    var key:String? = null

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
        Log.e("PriceCheckService", "onbind")
    }

    override fun onCreate() {
        super.onCreate()
        //key = getApiKey()
        key = "XLeZozdhkemWL4hl"
        Log.e("PriceCheckService", "servis sozdan")
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        powerManagment()
        isRunning = true
            CoroutineScope(SupervisorJob()).launch(Dispatchers.IO) {
                repeat(100_000) {
                    delay(10000L)
                    try {
                        if (key?.length!! < 16) {
                            Toast.makeText(applicationContext, "Enter api key", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            fetchData()
                        }

                    } catch (e: Exception) {
                        Log.e("PriceCheckService", "fetchdata exception: " + e.toString())
                    }
                }
            }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
//        createNotificationChannel()
//        var builder = notification()
//        with(NotificationManagerCompat.from(applicationContext)) {
//            notify(NOTIFICATION_ID, builder.build())
//        }
        isRunning = false

    }


    fun notification(
        acronym: String,
        currentPrice: String
    ): NotificationCompat.Builder {

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_message)
            .setContentTitle(acronym)
            .setContentText("Current price: " + currentPrice)
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
        Log.e("PriceCheckService", "fetching started")
        var stocksData : MutableList<OneStockPosition> = mutableListOf()
        val key: String = "XLeZozdhkemWL4hl"
        val selections: String = "stocks"


        var mService: RetrofitServices = Common.retrofitService
        var fetchedData: MutableList<OneStockPosition> = mutableListOf()


        mService.getSharesList(selections, key!!).enqueue(object : Callback<AllStocks> {
            override fun onResponse(
                call: Call<AllStocks>,
                response: Response<AllStocks>
            ) {
                //fix if key false (surr in try?)
                var stocksData = response.body()!!.stocks
                Log.e("FETCHED", response.body()!!.stocks.toString())
                response.body()!!.stocks.forEach{ entry ->
                    fetchedData.add(OneStockPosition(
                        entry.value.acronym,
                    entry.value.currentPrice,
                    entry.value.name,
                    entry.value.stockId))
                }

                // make normal function
                val stocksSettingsDatabase =
                    SSDatabase.getInstance(applicationContext, applicationScope).ssDatabaseDao()

                CoroutineScope(SupervisorJob()).launch(Dispatchers.IO) {
                    var i: Int = 0
                    while (i <= 31) {
                        var existedStockSetting: StocksSettings? = stocksSettingsDatabase.get(i + 1)
                        existedStockSetting = StocksSettings(
                            existedStockSetting!!.settingsID,
                            existedStockSetting.lowPrice,
                            existedStockSetting.highPrice,
                            fetchedData[i].acronym,
                            fetchedData[i].currentPrice
                        )
                        stocksSettingsDatabase.update(existedStockSetting)
                        i++
                    }
                }

                // use CoroutineScope(SupervisorJob()) if memory leak
                // make normal loop & function
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
                                var builder = notification(
                                    stocksSettingsDatabase.get(i)!!.acronym,
                                    stocksSettingsDatabase.get(i)!!.currentPrice.toString()
                                )
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

    // send requests up to 180+ secs after phone sleep
    // check if service work when app is close
    // repair requests frequency
    fun powerManagment() {
        val wakeLock: PowerManager.WakeLock =
            (getSystemService(Context.POWER_SERVICE) as PowerManager).run {
                newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "SharesParserK::MyWakeLogTag").apply {
                    acquire()
                }
            }
    }

    fun getApiKey():String {
        var appSettings = getSharedPreferences(APP_PREFERENCES_KEY, MODE_PRIVATE)
        var keyFromSP = appSettings.getString(APP_PREFERENCES_KEY, "").toString()
        Log.e("keycheck", keyFromSP.toString())
        return keyFromSP
    }


}




