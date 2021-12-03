package com.example.sharesparserk.Interface


import com.example.sharesparserk.PriceCheckService
import com.example.sharesparserk.model.AllStocks
import com.example.sharesparserk.model.Stocks
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface RetrofitServices {

    @GET("https://api.torn.com/torn/")
    fun getSharesList(@Query("selections") selections: String, @Query("key") key: String): Call<AllStocks>

}
