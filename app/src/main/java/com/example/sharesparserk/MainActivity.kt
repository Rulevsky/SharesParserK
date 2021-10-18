package com.example.sharesparserk

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.example.sharesparserk.Common.Common
import com.example.sharesparserk.Interface.RetrofitServices
import com.example.sharesparserk.SettingsAct.SettingsActivity


import com.example.sharesparserk.database.SettingsDatabase
import com.example.sharesparserk.database.SettingsForStocks
import com.example.sharesparserk.database.Stock
import com.example.sharesparserk.database.StocksDatabase
import com.example.sharesparserk.model.AllStocks
import com.example.sharesparserk.model.OneStockPosition
import com.example.sharesparserk.model.Stocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var mService: RetrofitServices
    //lateinit var dialog: AlertDialog
    var dataset: MutableList<OneStockPosition> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceClass = PriceCheckService::class.java
        val intent = Intent(this, serviceClass)
        startService(intent)

    }


    fun goToPricesActivity(view: android.view.View) {
        var intent = Intent(this, PricesActivity::class.java)
        startActivity(intent)
    }

    private suspend fun insertSettings(){
////        var set = SettingsForStocks(1, "ABC", 2.11, 999.11)
//        var settingsDatabase = SettingsDatabase.getInstance(this).settingsDatabaseDao
//        if (settingsDatabase.get(1) == null){
//            settingsDatabase.insert(set)
//        } else {
//            settingsDatabase.update(set)
//        }
//        Log.e("proverkaSettings", set.highPrice.toString() + "<-max, min ->" + set.lowPrice.toString())
    }

    fun goToSecondActivity(view: View) {
        startActivity(Intent(this, SettingsActivity::class.java))

    }


    private suspend fun insertListToDb(dataset: MutableList<OneStockPosition>) {
        var stocksDatabase = StocksDatabase.getInstance(this).stocksDatabaseDao
        stocksDatabase.clear()
        var i = 0
        while (i < dataset.size){
            stocksDatabase.insert(Stock(dataset.get(i).stockId, dataset.get(i).acronym, dataset.get(i).name, dataset.get(i).currentPrice))
            Log.e("iterator", i.toString())
            Log.e("size", dataset.size.toString())
            i++
        }
    }

    fun isRunningBtn(view: android.view.View) {

    }


}