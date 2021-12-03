package com.example.sharesparserk.StocksViewModel

import androidx.annotation.WorkerThread
import com.example.sharesparserk.database1.SSDatabaseDao
import com.example.sharesparserk.database1.StocksSettings
import kotlinx.coroutines.flow.Flow

class StocksSettingsRepository (
    private val ssDatabaseDao: SSDatabaseDao,
    ) {
        val allStocksSettings: Flow<List<StocksSettings>> = ssDatabaseDao.getAllStocksSettings()

        @WorkerThread
        suspend fun insert(stocksSettings: StocksSettings){
            ssDatabaseDao.insert(stocksSettings)
        }
        @WorkerThread
        suspend fun update(stocksSettings: StocksSettings){
            ssDatabaseDao.update(stocksSettings)
        }

}