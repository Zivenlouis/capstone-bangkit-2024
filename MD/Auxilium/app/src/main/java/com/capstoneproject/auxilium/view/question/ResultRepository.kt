package com.capstoneproject.auxilium.view.question

import com.capstoneproject.auxilium.api.ApiConfig.Companion.getApiService
import com.capstoneproject.auxilium.api.ApiService
import com.capstoneproject.auxilium.api.WishlistRequest
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.AddWishlistResponse
import com.capstoneproject.auxilium.response.PhonesResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

class ResultRepository(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
) {

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

    suspend fun addWishlist(userId: Int, smartphoneId: Int): AddWishlistResponse {
        return apiService.addWishlist(WishlistRequest(userId, smartphoneId))
    }
}