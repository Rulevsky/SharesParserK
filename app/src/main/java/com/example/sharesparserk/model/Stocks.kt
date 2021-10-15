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
    @SerializedName("4")
    var x4: OneStockPosition,
    @SerializedName("5")
    var x5: OneStockPosition,
    @SerializedName("6")
    var x6: OneStockPosition,
    @SerializedName("7")
    var x7: OneStockPosition,
    @SerializedName("8")
    var x8: OneStockPosition,
    @SerializedName("9")
    var x9: OneStockPosition,
    @SerializedName("10")
    var x10: OneStockPosition,
    @SerializedName("11")
    var x11: OneStockPosition,
    @SerializedName("12")
    var x12: OneStockPosition,
    @SerializedName("13")
    var x13: OneStockPosition,
    @SerializedName("14")
    var x14: OneStockPosition,
    @SerializedName("15")
    var x15: OneStockPosition,
    @SerializedName("16")
    var x16: OneStockPosition,
    @SerializedName("17")
    var x17: OneStockPosition,
    @SerializedName("18")
    var x18: OneStockPosition,
    @SerializedName("19")
    var x19: OneStockPosition,
    @SerializedName("20")
    var x20: OneStockPosition,
    @SerializedName("21")
    var x21: OneStockPosition,
    @SerializedName("22")
    var x22: OneStockPosition,
    @SerializedName("23")
    var x23: OneStockPosition,
    @SerializedName("24")
    var x24: OneStockPosition,
    @SerializedName("25")
    var x25: OneStockPosition,
    @SerializedName("26")
    var x26: OneStockPosition,
    @SerializedName("27")
    var x27: OneStockPosition,
    @SerializedName("28")
    var x28: OneStockPosition,
    @SerializedName("29")
    var x29: OneStockPosition,
    @SerializedName("30")
    var x30: OneStockPosition,
    @SerializedName("31")
    var x31: OneStockPosition,
    @SerializedName("32")
    var x32: OneStockPosition,
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