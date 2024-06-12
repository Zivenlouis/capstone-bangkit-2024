package com.capstoneproject.auxilium.view.newarrivals

import android.util.Log
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
                response
            } catch (e: Exception) {
                Log.e("HomeRepository", "Error fetching phones: ${e.localizedMessage}")
                emptyList()
            }
        }
    }
}