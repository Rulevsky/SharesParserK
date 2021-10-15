package com.example.sharesparserk.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "stocks_price_table")
data class Stock (
    @PrimaryKey(autoGenerate = false)
    var stock_id: Int,

    @ColumnInfo(name = "acronym")
    var acronym: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "currentPrice")
    var currentPrice: Double,

//    @ColumnInfo(name = "stock_id")
//    var stock_id: Int
)

