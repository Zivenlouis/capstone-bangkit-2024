package com.capstoneproject.auxilium.login

import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.api.LoginRequestBody
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.LoginResponse
import kotlinx.coroutines.flow.Flow

class LoginRepository(private val authDataStore: UserPreference) {

    suspend fun login(email: String, password: String): LoginResponse {
        val apiService = ApiConfig.getApiService(null)
        val requestBody = LoginRequestBody(email, password)
        val response = apiService.login(requestBody)

        response.token?.let { token ->
            authDataStore.saveToken(token)
        }

        response.userId?.let { userId ->
            authDataStore.saveUserId(userId)
        }

        return response
    }

    fun getSavedToken(): Flow<String?> {
        return authDataStore.getToken()
    }

    suspend fun saveToken(token: String) {
        authDataStore.saveToken(token)
    }

    suspend fun saveUserId(userId: Int) {
        authDataStore.saveUserId(userId)
    }
}
