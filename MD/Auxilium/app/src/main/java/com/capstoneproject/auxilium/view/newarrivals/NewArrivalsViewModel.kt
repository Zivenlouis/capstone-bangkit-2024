package com.capstoneproject.auxilium.view.newarrivals

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.PhonesResponseItem
import com.capstoneproject.auxilium.ui.home.HomeRepository
import kotlinx.coroutines.launch

class NewArrivalsViewModel(userPreference: UserPreference) : ViewModel() {
    private val repository = HomeRepository(userPreference)
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
}

class NewArrivalsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewArrivalsViewModel::class.java)) {
            val userPreference = UserPreference.getInstance(context)
            return NewArrivalsViewModel(userPreference) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
