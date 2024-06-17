package com.capstoneproject.auxilium.view.newarrivals

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneproject.auxilium.response.PhonesResponseItem
import kotlinx.coroutines.launch

class NewArrivalsViewModel(private val repository: NewArrivalsRepository) : ViewModel() {

    private val errorMessage = MutableLiveData<String?>()
    val phoneList = MutableLiveData<List<PhonesResponseItem>?>()

    fun fetchPhones() {
        viewModelScope.launch {
            try {
                val phones = repository.getPhones()
                phoneList.postValue(phones)
            } catch (e: Exception) {
                errorMessage.postValue("Error fetching phones: ${e.message}")
            }
        }
    }

    fun addUserClick(userId: Int, smartphoneId: Int) {
        viewModelScope.launch {
            try {
                repository.addUserClick(userId, smartphoneId)
            } catch (e: Exception) {
                errorMessage.postValue("Error adding user click: ${e.message}")
            }
        }
    }
}
