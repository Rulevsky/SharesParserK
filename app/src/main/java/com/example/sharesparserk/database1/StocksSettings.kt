package com.example.sharesparserk.database1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "stocks_settings_table")
class StocksSettings(

    @PrimaryKey(autoGenerate = false)
    var settingsID: Int,
    @ColumnInfo(name = "Low price")
    var lowPrice: Double,
    @ColumnInfo(name = "High price")
    var highPrice: Double,
    @ColumnInfo(name = "Acronym")
    var acronym: String,
    @ColumnInfo(name = "Current Price")
    var currentPrice: Double,



)
{
}