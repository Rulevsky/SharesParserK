package com.example.sharesparserk


import PriceRecAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.sharesparserk.Common.Common
import com.example.sharesparserk.Interface.RetrofitServices
import com.example.sharesparserk.database.Stock


import com.example.sharesparserk.database.StocksDatabase
import com.example.sharesparserk.model.AllStocks
import com.example.sharesparserk.model.OneStockPosition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PricesActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var priceRecAdapter: PriceRecAdapter
    lateinit var mService: RetrofitServices


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prices)
        Log.e("Pr", "price activity oncreated")

//         завести рум
        var stocksDatabase = StocksDatabase.getInstance(this).stocksDatabaseDao

        var mService: RetrofitServices = Common.retrofitService
        var dataSet: MutableList<OneStockPosition> = mutableListOf()
        mService.getSharesList().enqueue(object : Callback<AllStocks> {
            override fun onResponse(
                call: Call<AllStocks>,
                response: Response<AllStocks>
            ) {
                //GetData.stocksToList(response.body()!!)
                var stocksData = response.body()!!
                Log.e("tag", "responsebody zapisalsa: " + stocksData.stocks.x1.toString())
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

                recyclerView = findViewById(R.id.priceRecyclerView)
                recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                val stockAdapter = PriceRecAdapter(dataSet)
                recyclerView.adapter = stockAdapter
                GlobalScope.launch(Dispatchers.IO) {
                    var i: Int = 0
                    while (i < dataSet.size) {
                        if (stocksDatabase.get(i+1)?.stock_id == null) {
                            stocksDatabase.insert(
                                Stock(
                                    dataSet.get(i).stockId,
                                    dataSet.get(i).acronym,
                                    dataSet.get(i).name,
                                    dataSet.get(i).currentPrice
                                )
                            )

                            Log.e("prices activity db inserted", i.toString())

                        } else {
                            stocksDatabase.update(
                                Stock(
                                    dataSet.get(i).stockId,
                                    dataSet.get(i).acronym,
                                    dataSet.get(i).name,
                                    dataSet.get(i).currentPrice
                                )
                            )
                        }
                    i++

                    }

                }


            }

            override fun onFailure(call: Call<AllStocks>, t: Throwable) {
                Log.e("tag", "failure: " + t.toString())
            }
        })

    }


    fun getStockDb() {
        var db = StocksDatabase.getInstance(applicationContext).stocksDatabaseDao

    }


//    fun getDataSet(): MutableList<OneStockPosition> {
//        var dataset: MutableList<OneStockPosition> = mutableListOf()
//        var stockData: AllStocks? = GetData.getAllSharesList()
//        dataset = stockData?.let { GetData.stocksToList(it) }!!
//        return dataset
//    }

}