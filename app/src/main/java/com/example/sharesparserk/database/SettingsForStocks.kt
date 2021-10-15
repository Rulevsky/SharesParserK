package com.example.sharesparserk.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings_table")
data class SettingsForStocks (
    @PrimaryKey(autoGenerate = false)
    var settingsID: Int,
    @ColumnInfo( name = "acronym")
    var acronymid1: String,
    @ColumnInfo(name = "Low price")
    var lowPrice: Double,
    @ColumnInfo(name = "High price")
    var highPrice: Double,




    )