package com.capstoneproject.auxilium.login

import androidx.lifecycle.ViewModel
import com.capstoneproject.auxilium.response.LoginResponse
import kotlinx.coroutines.flow.Flow

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    suspend fun login(email: String, password: String): LoginResponse {
        return repository.login(email, password)
    }

    fun getSavedToken(): Flow<String?> {
        return repository.getSavedToken()
    }

    suspend fun saveToken(token: String) {
        repository.saveToken(token)
    }

    suspend fun saveUserId(userId: Int) {
        repository.saveUserId(userId)
    }
}
