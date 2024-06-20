package com.capstoneproject.auxilium.ui.wishlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.PhonesResponseItem
import com.capstoneproject.auxilium.response.WishlistResponseItem
import kotlinx.coroutines.launch

class WishlistViewModel(userPreference: UserPreference) : ViewModel() {
    private val repository = WishlistRepository(userPreference)
    val wishlist = MutableLiveData<List<Pair<WishlistResponseItem, PhonesResponseItem?>>?>()
    private val errorMessage = MutableLiveData<String?>()
    val deleteSuccess = MutableLiveData<Boolean>()

    fun fetchWishlist(userId: Int) {
        viewModelScope.launch {
            try {
                val wishlistItems = repository.getWishlist(userId)
                val wishlistWithPhones = wishlistItems.map { item ->
                    val phone = repository.getPhoneById(item.smartphoneId ?: 0)
                    item to phone
                }
                if (wishlistWithPhones.isNotEmpty()) {
                    wishlist.postValue(wishlistWithPhones)
                } else {
                    wishlist.postValue(emptyList())
                }
            } catch (e: Exception) {
                errorMessage.postValue("Error fetching wishlist: ${e.message}")
            }
        }
    }

    fun deleteWishlistById(wishlistId: Int) {
        viewModelScope.launch {
            try {
                val wishlistItem = fetchWishlistById(wishlistId)
                if (wishlistItem != null) {
                    val success = repository.deleteWishlistById(wishlistId)
                    if (success) {
                        deleteSuccess.postValue(true)
                        fetchWishlist(wishlistItem.userId!!)
                    } else {
                        errorMessage.postValue("Error deleting wishlist")
                    }
                } else {
                    errorMessage.postValue("Wishlist not found")
                }
            } catch (e: Exception) {
                errorMessage.postValue("Error deleting wishlist: ${e.message}")
            }
        }
    }

    fun deleteWishlistByIdAndNotify(wishlistId: Int, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val wishlistItem = fetchWishlistById(wishlistId)
                if (wishlistItem != null) {
                    val success = repository.deleteWishlistById(wishlistId)
                    if (success) {
                        onSuccess()
                        deleteSuccess.postValue(true)
                        fetchWishlist(wishlistItem.userId!!)
                    } else {
                        errorMessage.postValue("Error deleting wishlist")
                    }
                } else {
                    errorMessage.postValue("Wishlist not found")
                }
            } catch (e: Exception) {
                errorMessage.postValue("Error deleting wishlist: ${e.message}")
            }
        }
    }

    private suspend fun fetchWishlistById(wishlistId: Int): WishlistResponseItem? {
        return repository.getWishlistById(wishlistId)
    }
}
