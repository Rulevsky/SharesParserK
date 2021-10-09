package com.example.sharesparserk.model

import com.google.gson.annotations.SerializedName

data class OneStockPosition(
    @SerializedName("acronym")
    var acronym: String,

    @SerializedName("current_price")
    var currentPrice: Double,

    @SerializedName("name")
    var name: String,

    @SerializedName("stock_id")
    var stockId: Int,
) {

}