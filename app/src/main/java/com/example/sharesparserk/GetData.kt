package com.example.sharesparserk

import android.util.Log
import com.example.sharesparserk.Common.Common
import com.example.sharesparserk.Interface.RetrofitServices
import com.example.sharesparserk.model.AllStocks
import com.example.sharesparserk.model.OneStockPosition
import com.example.sharesparserk.model.Stocks
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GetData {

    var stocksList: MutableList<OneStockPosition>? = mutableListOf()



    fun getAllSharesList(): AllStocks? {
        var stocksData: AllStocks? = null
        var mService: RetrofitServices = Common.retrofitService

        mService.getSharesList().enqueue(object : Callback<AllStocks> {
            override fun onResponse(
                call: Call<AllStocks>,
                response: Response<AllStocks>
            ) {
                stocksToList(response.body()!!)
                stocksData = response.body()!!
                Log.e("tag", "responsebody zapisalsa: " + stocksData!!.stocks.x1.toString())


            }

            override fun onFailure(call: Call<AllStocks>, t: Throwable) {
                Log.e("tag", "failure: " + t.toString())
            }
        })
        return stocksData
    }

//    fun check() {
//
//            Log.e("tag", "sdsadsd")
//            Log.e("tag", stocksList?.size.toString())
//            Log.e("tag", stocksList?.get(1).toString())
//
//    }

    fun stocksToList(stocksData: AllStocks): MutableList<OneStockPosition> {


        stocksList?.add(stocksData.stocks.x1)
        stocksList?.add(stocksData.stocks.x2)
        stocksList?.add(stocksData.stocks.x3)
        Log.e("stl", stocksList?.size.toString())
        Log.e("stl", stocksList?.get(0).toString())
        Log.e("stl", stocksList?.get(2).toString())

        return stocksList!!
    }


}