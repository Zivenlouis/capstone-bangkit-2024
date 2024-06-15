package com.capstoneproject.auxilium.view.question.detailresult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneproject.auxilium.response.AddWishlistResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailResultViewModel(private val repository: DetailResultRepository) : ViewModel() {
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
                    _error.value = "Item sudah ada di wishlist"
                } else {
                    _error.value = "Failed to add to wishlist: ${e.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Failed to add to wishlist: ${e.message}"
            }
        }
    }
}
