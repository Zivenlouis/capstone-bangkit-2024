package com.capstoneproject.auxilium.ui.home

import android.util.Log
import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.api.ApiService
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.GetUsersResponseItem
import com.capstoneproject.auxilium.response.PhonesResponseItem
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
            Log.d("HomeRepository", "getUserById response: $response")
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
                response
            } catch (e: Exception) {
                Log.e("HomeRepository", "Error fetching phones: ${e.localizedMessage}")
                emptyList()
            }
        }
    }
}

