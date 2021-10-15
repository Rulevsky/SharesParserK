package com.example.sharesparserk.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SettingsDatabaseDao {
    @Insert
    fun insert(settings: SettingsForStocks)
    @Update
    fun update(settings: SettingsForStocks)
    @Query("SELECT * from settings_table WHERE settingsID = :key")
    fun get(key: Int):SettingsForStocks?
    @Query("DELETE FROM settings_table")
    fun clear()

}