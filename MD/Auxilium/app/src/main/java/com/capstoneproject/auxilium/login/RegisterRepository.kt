package com.capstoneproject.auxilium.login

import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.api.RegisterRequestBody
import com.capstoneproject.auxilium.response.RegisterResponse
import kotlinx.coroutines.flow.firstOrNull

class RegisterRepository(private val authDataStore: UserPreference) {

    suspend fun register(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): RegisterResponse {
        val token = authDataStore.getToken().firstOrNull() ?: ""
        val apiService = ApiConfig.getApiService(token)
        val requestBody = RegisterRequestBody(name, email, password, confirmPassword)
        return apiService.register(requestBody)
    }
}
