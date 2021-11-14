package com.example.sharesparserk.database1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SSDatabaseDao {
    @Insert
    suspend fun insert(stocksSettings: StocksSettings)
    @Update
    suspend fun update(stocksSettings: StocksSettings)
    @Query("SELECT * from stocks_settings_table WHERE settingsID = :key")
    suspend fun get(key: Int): StocksSettings?
    @Query("DELETE FROM stocks_settings_table")
    suspend fun clear()

    @Query("SELECT * from stocks_settings_table ORDER BY settingsID ASC" )
    fun getAllStocksSettings(): Flow<List<StocksSettings>>
}