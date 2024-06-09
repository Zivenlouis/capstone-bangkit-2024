package com.capstoneproject.auxilium.login

import androidx.lifecycle.ViewModel
import com.capstoneproject.auxilium.response.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterViewModel(private val repository: RegisterRepository) : ViewModel() {

    suspend fun register(username: String, email: String, password: String, confirmPassword: String): RegisterResponse {
        return withContext(Dispatchers.IO) {
            repository.register(username, email, password, confirmPassword)
        }
    }

}