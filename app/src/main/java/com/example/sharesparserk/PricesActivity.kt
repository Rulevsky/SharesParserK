package com.example.sharesparserk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sharesparserk.Adapter.PriceRecAdapter
import com.example.sharesparserk.Common.Common
import com.example.sharesparserk.Interface.RetrofitServices
import com.example.sharesparserk.database.SettingsDatabase
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

class PricesActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var priceRecAdapter: PriceRecAdapter
    lateinit var mService: RetrofitServices



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prices)



        var mService: RetrofitServices = Common.retrofitService
        var dataSet: MutableList<OneStockPosition> = mutableListOf()
        mService.getSharesList().enqueue(object : Callback<AllStocks> {
            override fun onResponse(
                call: Call<AllStocks>,
                response: Response<AllStocks>
            ) {
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

                lifecycleScope.launch(Dispatchers.IO) {
                    insertStockToDb(stocksData.stocks)
                }


                recyclerView = findViewById(R.id.priceRecyclerView)
                recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                recyclerView.adapter = PriceRecAdapter(dataSet)
            }

            override fun onFailure(call: Call<AllStocks>, t: Throwable) {
                Log.e("tag", "failure: " + t.toString())
            }
        })

    }

    private suspend fun insertStockToDb(stocks: Stocks) {

        var stock: Stock = Stock(
            stocks.x16.stockId,
            stocks.x16.acronym,
            stocks.x16.name,
            stocks.x16.currentPrice,

        )
        var stocksDatabase = StocksDatabase.getInstance(this).stocksDatabaseDao

        if (stocksDatabase.get(16) == null){
            stocksDatabase.insert(stock)
        } else {
            stocksDatabase.update(stock)
        }
        stocksDatabase.get(0)
    }


    fun getDatabase() {

        lifecycleScope.launch {

        }
    }


}

