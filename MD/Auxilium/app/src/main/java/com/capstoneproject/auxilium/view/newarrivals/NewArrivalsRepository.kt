package com.capstoneproject.auxilium.view.newarrivals

import android.util.Log
import com.capstoneproject.auxilium.api.AddClickRequestBody
import com.capstoneproject.auxilium.api.ApiConfig.Companion.getApiService
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.PhonesResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

class NewArrivalsRepository(private val userPreference: UserPreference) {

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
                Log.e("NewArrivalsRepository", "Error fetching phones: ${e.localizedMessage}")
                throw e
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
                Log.e("NewArrivalsRepository", "Error adding user click: ${e.localizedMessage}")
                throw e
            }
        }
    }
}
