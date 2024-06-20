package com.capstoneproject.auxilium.view.question

import android.util.Log
import com.capstoneproject.auxilium.api.AddClickRequestBody
import com.capstoneproject.auxilium.api.AddRatingRequestBody
import com.capstoneproject.auxilium.api.ApiConfig.Companion.getApiService
import com.capstoneproject.auxilium.api.ApiService
import com.capstoneproject.auxilium.api.WishlistRequest
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.AddRatingsResponse
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

    suspend fun addUserClick(userId: Int, smartphoneId: Int) {
        val token = userPreference.getToken().firstOrNull()
        if (token.isNullOrEmpty()) {
            return
        }

        withContext(Dispatchers.IO) {
            val apiService = getApiService(token)
            try {
                val requestBody = AddClickRequestBody(userId, smartphoneId)
                apiService.addUserClick(requestBody)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    suspend fun addWishlist(userId: Int, smartphoneId: Int): AddWishlistResponse {
        return apiService.addWishlist(WishlistRequest(userId, smartphoneId))
    }

    suspend fun addUserRating(userId: Int, smartphoneId: Int, rating: Char): AddRatingsResponse {
        return apiService.addUserRating(AddRatingRequestBody(userId, smartphoneId, rating))
    }

}