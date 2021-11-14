package com.example.sharesparserk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharesparserk.Interface.RetrofitServices

import com.example.sharesparserk.StocksViewModel.StocksActivity


import com.example.sharesparserk.model.OneStockPosition
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    lateinit var mService: RetrofitServices

    //lateinit var dialog: AlertDialog
    var dataset: MutableList<OneStockPosition> = mutableListOf()
    val applicationScope = CoroutineScope(SupervisorJob())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceClass = PriceCheckService::class.java
        val intent = Intent(this, serviceClass)
        startService(intent)

    }

    fun isRunningBtn(view: android.view.View) {
        var intent = Intent(this, StocksActivity::class.java)
        startActivity(intent)

    }


}