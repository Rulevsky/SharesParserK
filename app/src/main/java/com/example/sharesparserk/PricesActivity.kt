package com.example.sharesparserk

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sharesparserk.Adapter.PriceRecAdapter
import com.example.sharesparserk.Common.Common
import com.example.sharesparserk.Interface.RetrofitServices
import com.example.sharesparserk.database.StocksDatabase
import com.example.sharesparserk.database.StocksDatabaseDao
import com.example.sharesparserk.model.AllStocks
import com.example.sharesparserk.model.OneStockPosition
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
        var stocksDatabase = StocksDatabase.getInstance(this)




        var mService: RetrofitServices = Common.retrofitService
        var dataSet: MutableList<OneStockPosition> = mutableListOf()
        mService.getSharesList().enqueue(object : Callback<AllStocks> {
            override fun onResponse(
                call: Call<AllStocks>,
                response: Response<AllStocks>
            ) {
                GetData.stocksToList(response.body()!!)
                var stocksData = response.body()!!
                Log.e("tag", "responsebody zapisalsa: " + stocksData.stocks.x1.toString())
                dataSet = mutableListOf(stocksData.stocks.x1, stocksData.stocks.x2, stocksData.stocks.x3)
                recyclerView = findViewById(R.id.priceRecyclerView)
                recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                recyclerView.adapter = PriceRecAdapter(dataSet)
            }

            override fun onFailure(call: Call<AllStocks>, t: Throwable) {
                Log.e("tag", "failure: " + t.toString())
            }
        })




    }


    fun getDatabase(){


    }

//    fun initRecyclerView() {
//        Log.e("pr", "init rec view")
//        var dataset: MutableList<OneStockPosition> = mutableListOf()
//        dataset = getDataSet()
//        Log.e("pr", "init recycler: " + dataset.size.toString())
//        var adapter = PriceRecAdapter(dataset)
//        recyclerView = findViewById(R.id.priceRecyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = adapter
//
//    }

//    fun getDataSet(): MutableList<OneStockPosition> {
//        var dataset: MutableList<OneStockPosition> = mutableListOf()
//        var stockData: AllStocks? = GetData.getAllSharesList()
//        dataset = stockData?.let { GetData.stocksToList(it) }!!
//        return dataset
//    }
//
//    private var job: Job = Job()
//    private var scope =
//        CoroutineScope(Dispatchers.Main + job) // creating the scope to run the coroutine. It consists of Dispatchers.Main (coroutine will run in the Main context) and job to handle the cancellation of the coroutine.
//
//    fun runInParallel() {
//        scope.launch { // launch a coroutine
//            // runs in parallel
//            val deferredList = listOf(
//                scope.async { getDataSet() }
//            )
//            delay(1000L)
//            deferredList.awaitAll() // wait for all data to be processed without blocking the UI thread
//            initRecyclerView()
//            // do some stuff after data has been processed, for example update UI
//        }
//    }
//
//
//    fun cancel() {
//        job.cancel() // invoke it to cancel the job when you don't need it to execute. For example when UI changed and you don't need to process data
//    }
}