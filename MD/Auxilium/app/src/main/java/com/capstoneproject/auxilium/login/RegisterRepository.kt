package com.capstoneproject.auxilium.login

import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.RegisterResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.firstOrNull
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File

class RegisterRepository(private val authDataStore: UserPreference) {

    suspend fun register(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        file: File
    ): RegisterResponse {
        val token = authDataStore.getToken().firstOrNull() ?: ""
        val apiService = ApiConfig.getApiService(token)

        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val filePart = MultipartBody.Part.createFormData("file", file.name, requestFile)

        val namePart = name.toRequestBody("text/plain".toMediaTypeOrNull())
        val emailPart = email.toRequestBody("text/plain".toMediaTypeOrNull())
        val passwordPart = password.toRequestBody("text/plain".toMediaTypeOrNull())
        val confirmPasswordPart = confirmPassword.toRequestBody("text/plain".toMediaTypeOrNull())

        return try {
            apiService.register(namePart, emailPart, passwordPart, confirmPasswordPart, filePart)
        } catch (e: HttpException) {
            val errorResponse = e.response()?.errorBody()?.string()
            val registerResponse = Gson().fromJson(errorResponse, RegisterResponse::class.java)
            throw RegistrationException(registerResponse.message ?: "Unknown error")
        }
    }
}

class RegistrationException(message: String) : Exception(message)
