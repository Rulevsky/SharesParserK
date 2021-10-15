package com.example.sharesparserk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.sharesparserk.Interface.RetrofitServices

import com.example.sharesparserk.database.SettingsDatabase
import com.example.sharesparserk.database.SettingsForStocks
import com.example.sharesparserk.database.StocksDatabase
import com.example.sharesparserk.model.OneStockPosition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var mService: RetrofitServices
    //lateinit var dialog: AlertDialog
    var dataset: MutableList<OneStockPosition> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        lifecycleScope.launch(Dispatchers.IO){
            insertSettings()
        }



        Log.e("Tag", dataset.size.toString())
        //service
        val serviceClass = PriceCheckService::class.java
        val intent = Intent(this, serviceClass)
        startService(intent)

    }

    fun goToPricesActivity(view: android.view.View) {
        var intent = Intent(this, PricesActivity::class.java)
        startActivity(intent)
    }

    private suspend fun insertSettings(){
        var set = SettingsForStocks(1, "ABC", 2.11, 999.11)
        var settingsDatabase = SettingsDatabase.getInstance(this).settingsDatabaseDao
        if (settingsDatabase.get(1) == null){
            settingsDatabase.insert(set)
        } else {
            settingsDatabase.update(set)
        }
        Log.e("proverkaSettings", set.highPrice.toString() + "<-max, min ->" + set.lowPrice.toString())
    }

}