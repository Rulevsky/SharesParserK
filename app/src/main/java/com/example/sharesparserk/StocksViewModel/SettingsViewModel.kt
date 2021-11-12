package com.example.sharesparserk.StocksViewModel

import androidx.lifecycle.*
import com.example.sharesparserk.database.SettingsForStocks
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SettingsViewModel(private val repository: SettingsRepository) : ViewModel() {

    val allSettingsRepository: LiveData<List<SettingsForStocks>> = repository.allSettigs.asLiveData()

    fun insert(settingsForStocks: SettingsForStocks) = viewModelScope.launch {
        repository.update(settingsForStocks)
    }
    fun update(settingsForStocks: SettingsForStocks) = viewModelScope.launch {
        repository.update(settingsForStocks)
    }
}


class SettingsViewModelFactory(private val repository: SettingsRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}