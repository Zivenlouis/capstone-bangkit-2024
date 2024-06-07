package com.capstoneproject.auxilium.api

import com.capstoneproject.auxilium.response.CommunityResponseItem
import com.capstoneproject.auxilium.response.GetUsersResponse
import com.capstoneproject.auxilium.response.LoginResponse
import com.capstoneproject.auxilium.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("users")
    suspend fun register(
        @Body requestBody: RegisterRequestBody
    ): RegisterResponse

    @POST("login")
    suspend fun login(
        @Body requestBody: LoginRequestBody
    ): LoginResponse

    @GET("community")
    suspend fun getAllForumPosts(): List<CommunityResponseItem>

    @GET("users")
    suspend fun getAllUsers(): GetUsersResponse
}

data class RegisterRequestBody(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
data class LoginRequestBody(
    val email: String,
    val password: String,
)


