package com.example.sharesparserk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sharesparserk.Common.Common
import com.example.sharesparserk.Interface.RetrofitServices
import com.example.sharesparserk.model.AllStocks
import com.example.sharesparserk.model.OneStockPosition
import com.example.sharesparserk.model.Stocks
import com.example.sharesparserk.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class MainActivity : AppCompatActivity() {
    lateinit var mService: RetrofitServices
    //lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = Common.retrofitService

    getAllSharesList()
    }


    fun getAllSharesList(){
        mService.getSharesList().enqueue(object: Callback<AllStocks>{
            override fun onResponse(
                call: Call<AllStocks>,
                response: Response<AllStocks>
            ) {
                var testList: AllStocks? = response.body()
                Log.e("tag", testList?.stocks?.size.toString())

            }

            override fun onFailure(call: Call<AllStocks>, t: Throwable) {
                Log.e("tag", "failure: " + t.toString())
            }

        })
    }

//    fun getTratata() {
//        val retrofServ: R
//    }

}