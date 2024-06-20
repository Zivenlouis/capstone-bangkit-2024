package com.capstoneproject.auxilium.ui.wishlist

import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.api.ApiService
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.PhonesResponseItem
import com.capstoneproject.auxilium.response.WishlistResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

class WishlistRepository(private val userPreference: UserPreference) {

    private fun getApiService(token: String): ApiService {
        return ApiConfig.getApiService(token)
    }

    suspend fun getWishlist(userId: Int): List<WishlistResponseItem> {
        val token = userPreference.getToken().firstOrNull()
        if (token.isNullOrEmpty()) {
            return emptyList()
        }

        return withContext(Dispatchers.IO) {
            try {
                val apiService = getApiService(token)
                val response = apiService.getWishlist(userId)
                response
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    suspend fun getPhoneById(phoneId: Int): PhonesResponseItem? {
        val token = userPreference.getToken().firstOrNull()
        if (token.isNullOrEmpty()) {
            return null
        }

        return withContext(Dispatchers.IO) {
            try {
                val apiService = getApiService(token)
                val response = apiService.getPhonesById(phoneId)
                response.firstOrNull()
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun deleteWishlistById(wishlistId: Int): Boolean {
        val token = userPreference.getToken().firstOrNull()
        if (token.isNullOrEmpty()) {
            return false
        }

        return withContext(Dispatchers.IO) {
            try {
                val apiService = getApiService(token)
                apiService.deleteWishlist(wishlistId)
                true
            } catch (e: Exception) {
                false
            }
        }
    }

    suspend fun getWishlistById(wishlistId: Int): WishlistResponseItem? {
        val token = userPreference.getToken().firstOrNull()
        if (token.isNullOrEmpty()) {
            return null
        }

        return withContext(Dispatchers.IO) {
            try {
                val apiService = getApiService(token)
                val response = apiService.getWishlistById(wishlistId)
                response
            } catch (e: Exception) {
                null
            }
        }
    }


}
