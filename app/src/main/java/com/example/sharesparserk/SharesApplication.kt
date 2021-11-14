package com.example.sharesparserk

import android.app.Application
import com.example.sharesparserk.StocksViewModel.StocksSettingsRepository
import com.example.sharesparserk.database1.SSDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SharesApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())
    val stocksSettingsDatabase by lazy { SSDatabase.getInstance(this, applicationScope)}
    val repository by lazy { StocksSettingsRepository(stocksSettingsDatabase.ssDatabaseDao()) }

}