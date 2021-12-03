package com.example.sharesparserk

import com.example.sharesparserk.AppSettingsActivity.AppSettingsActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.sharesparserk.Interface.RetrofitServices

import com.example.sharesparserk.StocksViewModel.StocksActivity


import com.example.sharesparserk.model.OneStockPosition
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    lateinit var mService: RetrofitServices
    lateinit var listBtn: Button
    lateinit var appSettingsBtn: Button
    val APP_PREFERENCES_KEY = "apikey"


    var dataset: MutableList<OneStockPosition> = mutableListOf()
    val applicationScope = CoroutineScope(SupervisorJob())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceClass = PriceCheckService::class.java
        val intent = Intent(this, serviceClass)
        startService(intent)

        listBtn = findViewById(R.id.listBtn)
        appSettingsBtn = findViewById(R.id.appSettingsBtn)

        listBtn.setOnClickListener{ onListBtn() }
        appSettingsBtn.setOnClickListener{ onAppSetingsBtn() }

  //      initNullApiKey()
    }

//    private fun initNullApiKey() {
//        var key:String? = null
//        var appSettings = getSharedPreferences(APP_PREFERENCES_KEY, MODE_PRIVATE)
//        appSettings.edit().putString(APP_PREFERENCES_KEY, key).apply()
//
//    }

    private fun onAppSetingsBtn() {
        var intent = Intent(this, AppSettingsActivity::class.java)
        startActivity(intent)
    }

    private fun onListBtn() {
        var intent = Intent(this, StocksActivity::class.java)
        startActivity(intent)
    }


}