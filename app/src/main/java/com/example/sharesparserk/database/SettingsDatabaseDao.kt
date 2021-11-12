package com.example.sharesparserk.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDatabaseDao {
    @Insert
    suspend fun insert(settings: SettingsForStocks)
    @Update
    suspend fun update(settings: SettingsForStocks)
    @Query("SELECT * from settings_table WHERE settingsID = :key")
    suspend fun get(key: Int):SettingsForStocks?
    @Query("DELETE FROM settings_table")
    suspend fun clear()

    @Query("SELECT * from settings_table ORDER BY settingsID ASC" )
    fun getSettingsSortedById(): Flow<List<SettingsForStocks>>

}