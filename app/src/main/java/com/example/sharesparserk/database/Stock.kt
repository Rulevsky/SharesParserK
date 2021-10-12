package com.example.sharesparserk.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "stocks_price_table")
data class Stock (
    @PrimaryKey(autoGenerate = true)
    var stockdbId: Long = 0L,

    @ColumnInfo(name = "acronym")
    var acronym: String,

    @ColumnInfo(name = "name")
    var name:String,

    @ColumnInfo(name = "currentPrice")
    var currentPrice: Long,

    @ColumnInfo(name = "stock_id")
    var stock_id: Int
)

