package com.example.sharesparserk

import android.app.Application
import com.example.sharesparserk.StocksViewModel.SettingsRepository
import com.example.sharesparserk.database.SettingsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SharesApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())
    val settingsDatabase by lazy {SettingsDatabase.getInstance(this, applicationScope)}
    val repository by lazy { SettingsRepository(settingsDatabase.settingsDatabaseDao())}
}