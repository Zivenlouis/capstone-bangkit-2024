package com.capstoneproject.auxilium.view.question.quest

import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.api.ApiService
import com.capstoneproject.auxilium.api.UserSurveyRequest
import com.capstoneproject.auxilium.datastore.UserPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

class QuestionnaireRepository(private val userPreference: UserPreference) {
    private lateinit var apiService: ApiService

    private suspend fun initializeApiService() {
        val token = getToken()
        apiService = ApiConfig.getApiService(token)
    }

    private suspend fun getToken(): String? {
        return userPreference.getToken().firstOrNull()
    }

    suspend fun getRecommendations(userSurvey: UserSurveyRequest): List<Int> {
        if (!::apiService.isInitialized) {
            initializeApiService()
        }

        return withContext(Dispatchers.IO) {
            val response = apiService.getRecommendations(userSurvey).execute()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        }
    }
}
