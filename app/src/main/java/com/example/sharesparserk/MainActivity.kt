package com.example.sharesparserk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sharesparserk.Interface.RetrofitServices
import com.example.sharesparserk.database.StocksDatabase
import com.example.sharesparserk.model.OneStockPosition


class MainActivity : AppCompatActivity() {
    lateinit var mService: RetrofitServices
    //lateinit var dialog: AlertDialog
    var dataset: MutableList<OneStockPosition> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        //GetData.getAllSharesList()?.let { GetData.stocksToList(it) }?.let { dataset.addAll(it) }
        Log.e("Tag", dataset.size.toString())
        val serviceClass = PriceCheckService::class.java
        val intent = Intent(this, serviceClass)
        //startService(intent)

    }

    fun goToPricesActivity(view: android.view.View) {
        var intent = Intent(this, PricesActivity::class.java)
        startActivity(intent)
    }


//    fun getAllSharesList() {
//        mService = Common.retrofitService
//        mService.getSharesList().enqueue(object : Callback<AllStocks> {
//            override fun onResponse(
//                call: Call<AllStocks>,
//                response: Response<AllStocks>
//            ) {
//                var testList: AllStocks? = response.body()
//                Log.e("tag", testList?.stocks.toString())
//
//            }
//
//            override fun onFailure(call: Call<AllStocks>, t: Throwable) {
//                Log.e("tag", "failure: " + t.toString())
//            }
//
//        })
//
//    }


}