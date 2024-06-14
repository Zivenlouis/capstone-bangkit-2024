package com.capstoneproject.auxilium.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.PhonesResponseItem
import com.capstoneproject.auxilium.response.WishlistResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val userPreference: UserPreference) : ViewModel() {
    private val repository = HomeRepository(userPreference)

    val userName = MutableLiveData<String?>()
    val profileImage = MutableLiveData<String?>()
    val errorMessage = MutableLiveData<String?>()
    val phoneList = MutableLiveData<List<PhonesResponseItem>?>()

    val wishlist = MutableLiveData<List<WishlistResponseItem>>()
    val error = MutableLiveData<String?>()

    fun fetchUserName(userId: Int) {
        viewModelScope.launch {
            try {
                val user = repository.getUserById(userId)
                if (user != null) {
                    withContext(Dispatchers.Main) {
                        userName.value = user.name
                        profileImage.value = user.profileImage
                        user.name?.let { userPreference.saveUsername(it) }
                    }
                } else {
                    errorMessage.postValue("User not found")
                }
            } catch (e: Exception) {
                errorMessage.postValue("Error fetching user: ${e.message}")
            }
        }
    }

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

    fun fetchWishlist(userId: Int) {
        viewModelScope.launch {
            try {
                val wishlistItems = repository.getWishlist(userId)
                wishlist.postValue(wishlistItems)
            } catch (e: Exception) {
                error.postValue("Error fetching wishlist: ${e.localizedMessage}")
            }
        }
    }
}
