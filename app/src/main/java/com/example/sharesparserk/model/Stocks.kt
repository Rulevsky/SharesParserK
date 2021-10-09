package com.example.sharesparserk.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Stocks (
    //var ssstocks: MutableList<OneStockPosition> = mutableListOf<OneStockPosition>(),
    @SerializedName("1")
    var x1: OneStockPosition,
    @SerializedName("2")
    var x2: OneStockPosition,
    @SerializedName("3")
    var x3: OneStockPosition,
) {

}

class X1(
    @SerializedName("acronym")
    var acronym: String,

    @SerializedName("current_price")
    var currentPrice: Double,

    @SerializedName("name")
    var name: String,

    @SerializedName("stock_id")
    var stockId: Int
){
}

data class X2(
    @SerializedName("acronym")
    var acronym: String,

    @SerializedName("current_price")
    var currentPrice: Double,

    @SerializedName("name")
    var name: String,

    @SerializedName("stock_id")
    var stockId: Int
)

data class X3(
    @SerializedName("acronym")
    var acronym: String,

    @SerializedName("current_price")
    var currentPrice: Double,

    @SerializedName("name")
    var name: String,

    @SerializedName("stock_id")
    var stockId: Int
)