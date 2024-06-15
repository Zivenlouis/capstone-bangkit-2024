package com.capstoneproject.auxilium.view.question

import android.util.Log
import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.api.ApiConfig.Companion.getApiService
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.PhonesResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

class ResultRepository (private val userPreference: UserPreference) {

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
}