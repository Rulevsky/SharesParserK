package com.example.sharesparserk.Common

import com.example.sharesparserk.Interface.RetrofitServices
import com.example.sharesparserk.retrofit.RetrofitClient

object Common {
    private val BASE_URL = "https://api.torn.com/torn/?selections=stocks&key=uzmYj2niVZCDwZZZ"
    val retrofitService: RetrofitServices
    get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)

}