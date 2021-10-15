package com.example.sharesparserk.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sharesparserk.model.OneStockPosition

@Dao
interface StocksDatabaseDao {
    @Insert
    fun insert(stock: Stock)
    @Update
    fun update(stock: Stock)
    @Query("SELECT * from stocks_price_table WHERE stock_id = :key")
    fun get(key: Int):Stock?
    @Query("DELETE FROM stocks_price_table")
    fun clear()
}