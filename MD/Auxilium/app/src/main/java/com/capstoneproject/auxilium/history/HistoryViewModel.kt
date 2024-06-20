package com.capstoneproject.auxilium.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.PhonesResponseItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val historyDao = HistoryDatabase.getDatabase(application).historyDao()

    private val _repository = MutableStateFlow<HistoryRepository?>(null)
    val repository: StateFlow<HistoryRepository?> get() = _repository

    init {
        viewModelScope.launch {
            val token = UserPreference.getInstance(application).getToken().firstOrNull()
            val apiService = ApiConfig.getApiService(token)
            _repository.value = HistoryRepository(historyDao, apiService)
        }
    }

    private val _phones = MutableStateFlow<List<PhonesResponseItem>>(emptyList())
    val phones: StateFlow<List<PhonesResponseItem>> get() = _phones

    fun fetchPhones(ids: List<Int>) {
        viewModelScope.launch {
            repository.value?.let {
                val phoneDetails = it.getPhonesByIds(ids)
                _phones.value = phoneDetails
            }
        }
    }

    suspend fun getAllHistory(): List<History> {
        return repository.value?.getAllHistory().orEmpty()
    }

    fun deleteHistory(history: History) {
        viewModelScope.launch {
            repository.value?.deleteById(history.id)

            val updatedHistoryList = repository.value?.getAllHistory().orEmpty()

            val idsToFetch = updatedHistoryList.flatMap {
                listOf(it.id1, it.id2, it.id3, it.id4, it.id5, it.id6, it.id7, it.id8, it.id9, it.id10)
            }
            val updatedPhones = repository.value?.getPhonesByIds(idsToFetch).orEmpty()

            _phones.value = updatedPhones
        }
    }

}

