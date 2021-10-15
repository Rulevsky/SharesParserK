package com.example.sharesparserk.Interface


import com.example.sharesparserk.model.AllStocks
import com.example.sharesparserk.model.Stocks
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("https://api.torn.com/torn/?selections=stocks&key=uzmYj2niVZCDwZZZ")
    fun getSharesList(): Call<AllStocks>

}