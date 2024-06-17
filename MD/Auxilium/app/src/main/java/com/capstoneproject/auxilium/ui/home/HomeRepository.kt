package com.capstoneproject.auxilium.ui.home

import android.util.Log
import com.capstoneproject.auxilium.api.AddClickRequestBody
import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.api.ApiService
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.GetUsersResponseItem
import com.capstoneproject.auxilium.response.PhonesResponseItem
import com.capstoneproject.auxilium.response.WishlistResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

class HomeRepository(private val userPreference: UserPreference) {

    private fun getApiService(token: String): ApiService {
        return ApiConfig.getApiService(token)
    }

    suspend fun getUserById(userId: Int): GetUsersResponseItem? {
        val token = userPreference.getToken().firstOrNull()
        if (token.isNullOrEmpty()) {
            return null
        }

        return withContext(Dispatchers.IO) {
            val apiService = getApiService(token)
            val response = apiService.getUserById(userId)
            Log.d(
                "com.capstoneproject.auxilium.ui.home.HomeRepository",
                "getUserById response: $response"
            )
            if (response.isNotEmpty()) response[0] else null
        }
    }

    suspend fun getPhones(): List<PhonesResponseItem> {
        val token = userPreference.getToken().firstOrNull()
        if (token.isNullOrEmpty()) {
            return emptyList()
        }

        return withContext(Dispatchers.IO) {
            val apiService = getApiService(token)
            try {
                val response = apiService.getAllPhones()
                response.sortedByDescending { it.releaseDate }
            } catch (e: Exception) {
                Log.e(
                    "com.capstoneproject.auxilium.ui.home.HomeRepository",
                    "Error fetching phones: ${e.localizedMessage}"
                )
                emptyList()
            }
        }
    }

    suspend fun getWishlist(userId: Int): List<WishlistResponseItem> {
        val token = userPreference.getToken().firstOrNull()
        if (token.isNullOrEmpty()) {
            return emptyList()
        }

        return withContext(Dispatchers.IO) {
            val apiService = getApiService(token)
            try {
                val response = apiService.getWishlist(userId)
                response
            } catch (e: Exception) {
                Log.e(
                    "com.capstoneproject.auxilium.ui.home.HomeRepository",
                    "Error fetching wishlist: ${e.localizedMessage}"
                )
                emptyList()
            }
        }
    }

    suspend fun getPhonesByIds(ids: List<Int>): List<PhonesResponseItem> {
        val token = userPreference.getToken().firstOrNull()
        if (token.isNullOrEmpty()) {
            return emptyList()
        }

        return withContext(Dispatchers.IO) {
            val apiService = getApiService(token)
            try {
                val phones = mutableListOf<PhonesResponseItem>()
                for (id in ids) {
                    val response = apiService.getPhonesById(id)
                    if (response.isNotEmpty()) {
                        phones.add(response[0])
                    }
                }
                phones
            } catch (e: Exception) {
                Log.e("HomeRepository", "Error fetching phones by IDs: ${e.localizedMessage}")
                emptyList()
            }
        }
    }

    suspend fun getTopSmartphones(userId: Int): List<Int> {
        val token = userPreference.getToken().firstOrNull()
        if (token.isNullOrEmpty()) {
            return emptyList()
        }

        return withContext(Dispatchers.IO) {
            val apiService = getApiService(token)
            try {
                val response = apiService.getTopSmartphones(userId)
                response.body() ?: emptyList()
            } catch (e: Exception) {
                Log.e(
                    "HomeRepository",
                    "Error fetching top smartphones: ${e.localizedMessage}"
                )
                emptyList()
            }
        }
    }

    suspend fun addUserClick(userId: Int, smartphoneId: Int) {
        val token = userPreference.getToken().firstOrNull()
        if (token.isNullOrEmpty()) {
            return
        }

        withContext(Dispatchers.IO) {
            val apiService = ApiConfig.getApiService(token)
            try {
                val requestBody = AddClickRequestBody(userId, smartphoneId)
                apiService.addUserClick(requestBody)
            } catch (e: Exception) {
                Log.e("NewArrivalsRepository", "Error adding user click: ${e.localizedMessage}")
                throw e
            }
        }
    }
}
