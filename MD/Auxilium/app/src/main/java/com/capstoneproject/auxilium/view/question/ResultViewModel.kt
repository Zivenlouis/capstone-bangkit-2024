package com.capstoneproject.auxilium.view.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneproject.auxilium.response.PhonesResponseItem
import kotlinx.coroutines.launch

class ResultViewModel(private val repository: ResultRepository) : ViewModel() {

    private val _phoneList = MutableLiveData<List<PhonesResponseItem>>()
    val phoneList: LiveData<List<PhonesResponseItem>> get() = _phoneList

    fun fetchPhones(ids: List<Int>) {
        viewModelScope.launch {
            val phones = ids.mapNotNull { id ->
                repository.getPhoneById(id)
            }
            _phoneList.value = phones
        }
    }
}
