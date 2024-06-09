package com.capstoneproject.auxilium.login

import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.api.LoginRequestBody
import com.capstoneproject.auxilium.response.LoginResponse
import kotlinx.coroutines.flow.Flow

class LoginRepository(private val authDataStore: UserPreference) {

    suspend fun login(email: String, password: String): LoginResponse {
        val apiService = ApiConfig.getApiService(null)
        val requestBody = LoginRequestBody(email, password)
        val response = apiService.login(requestBody)
        response.accessToken?.let { token ->
            authDataStore.saveToken(token)
        }
        return response
    }

    fun getSavedToken(): Flow<String?> {
        return authDataStore.getToken()
    }
}
