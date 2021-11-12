package com.example.sharesparserk.StocksViewModel

import androidx.annotation.WorkerThread
import com.example.sharesparserk.database.SettingsDatabaseDao
import com.example.sharesparserk.database.SettingsForStocks
import kotlinx.coroutines.flow.Flow

class SettingsRepository(private val settingsDatabaseDao: SettingsDatabaseDao) {
    val allSettigs: Flow<List<SettingsForStocks>> = settingsDatabaseDao.getSettingsSortedById()

    @WorkerThread
    suspend fun insert(settingsForStocks: SettingsForStocks){
        settingsDatabaseDao.insert(settingsForStocks)
    }

    @WorkerThread
    suspend fun update(settingsForStocks: SettingsForStocks){
        settingsDatabaseDao.update(settingsForStocks)
    }




}