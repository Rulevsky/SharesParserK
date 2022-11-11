package com.example.sharesparserk.retrofit.Common


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.sharesparserk.retrofit.Interface.RetrofitServices
import com.example.sharesparserk.retrofit.RetrofitClient
import java.util.*

object Common {
    private val BASE_URL = "https://api.torn.com/torn/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}