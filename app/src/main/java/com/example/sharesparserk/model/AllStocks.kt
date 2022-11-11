package com.example.sharesparserk.model

import com.google.gson.annotations.SerializedName

class AllStocks(
    @SerializedName("stocks")
    var stocks: Map<String, OneStockPosition>

) {
}