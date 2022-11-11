package com.example.sharesparserk.StocksViewModel

import androidx.lifecycle.*
import com.example.sharesparserk.database1.StocksSettings
import kotlinx.coroutines.launch


class SettingsViewModel(private val repository: StocksSettingsRepository) : ViewModel() {

    val allStocksSettingsRepository: LiveData<List<StocksSettings>> = repository.allStocksSettings.asLiveData()

    fun insert(stocksSettings: StocksSettings) = viewModelScope.launch {
        repository.update(stocksSettings)
    }
    fun update(stocksSettings: StocksSettings) = viewModelScope.launch {
        repository.update(stocksSettings)
    }
}


class SettingsViewModelFactory(private val repository: StocksSettingsRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}