package com.capstoneproject.auxilium.view.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneproject.auxilium.response.AddRatingsResponse
import com.capstoneproject.auxilium.response.AddWishlistResponse
import com.capstoneproject.auxilium.response.PhonesResponseItem
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ResultViewModel(private val repository: ResultRepository) : ViewModel() {

    private val _phoneList = MutableLiveData<List<PhonesResponseItem>>()
    val phoneList: LiveData<List<PhonesResponseItem>> get() = _phoneList

    private val _addRatingsResult = MutableLiveData<AddRatingsResponse>()
    val addRatingsResult: LiveData<AddRatingsResponse> = _addRatingsResult

    private val errorMessage = MutableLiveData<String?>()

    fun fetchPhones(ids: List<Int>) {
        viewModelScope.launch {
            val phones = ids.mapNotNull { id ->
                repository.getPhoneById(id)
            }
            _phoneList.value = phones
        }
    }

    private val _addWishlistResult = MutableLiveData<AddWishlistResponse>()
    val addWishlistResult: LiveData<AddWishlistResponse> = _addWishlistResult

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun addWishlist(userId: Int, smartphoneId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.addWishlist(userId, smartphoneId)
                if (response.msg != null) {
                    _addWishlistResult.value = response
                } else {
                    _error.value = "Failed to add to wishlist"
                }
            } catch (e: HttpException) {
                val code = e.code()
                if (code == 400) {
                    _error.value = "Item is already on wishlist"
                } else {
                    _error.value = "Failed to add to wishlist: ${e.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Failed to add to wishlist: ${e.message}"
            }
        }
    }

    fun addUserRating(userId: Int, smartphoneId: Int, rating: Char) {
        viewModelScope.launch {
            try {
                val response = repository.addUserRating(userId, smartphoneId, rating)
                if (response.msg != null) {
                    _addRatingsResult.value = response
                } else {
                    _error.value = "Failed to add rating"
                }
            } catch (e: HttpException) {
                _error.value = "Failed to add rating: ${e.message()}"
            } catch (e: Exception) {
                _error.value = "Failed to add rating: ${e.message}"
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
