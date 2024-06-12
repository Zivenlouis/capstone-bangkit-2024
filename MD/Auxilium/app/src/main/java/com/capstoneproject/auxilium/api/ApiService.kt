package com.capstoneproject.auxilium.api

import com.capstoneproject.auxilium.response.AddWishlistResponse
import com.capstoneproject.auxilium.response.CommunityResponseItem
import com.capstoneproject.auxilium.response.DeleteWishlistResponse
import com.capstoneproject.auxilium.response.GetUsersResponseItem
import com.capstoneproject.auxilium.response.LoginResponse
import com.capstoneproject.auxilium.response.PhonesResponseItem
import com.capstoneproject.auxilium.response.RegisterResponse
import com.capstoneproject.auxilium.response.ResetPasswordResponse
import com.capstoneproject.auxilium.response.WishlistResponseItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("users")
    suspend fun register(
        @Body requestBody: RegisterRequestBody,
    ): RegisterResponse

    @POST("login")
    suspend fun login(
        @Body requestBody: LoginRequestBody,
    ): LoginResponse

    @GET("community")
    suspend fun getAllForumPosts(): List<CommunityResponseItem>

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: Int,
    ): List<GetUsersResponseItem>

    @GET("smartphones")
    suspend fun getAllPhones(): List<PhonesResponseItem>

    @POST("resetPassword/{id}")
    suspend fun resetPassword(
        @Path("id") userId: Int,
        @Body request: ResetPasswordRequest,
    ): ResetPasswordResponse

    @GET("wishlist/user/{id}")
    suspend fun getWishlist(
        @Path("id") userId: Int,
    ): List<WishlistResponseItem>

    @GET("smartphones/{id}")
    suspend fun getPhonesById(
        @Path("id") id: Int,
    ): List<PhonesResponseItem>

    @POST("wishlist/add")
    suspend fun addWishlist(
        @Body wishlistRequest: WishlistRequest,
    ): AddWishlistResponse

    @DELETE("wishlist/{id}")
    suspend fun deleteWishlist(
        @Path("id") id: Int,
    ): DeleteWishlistResponse

    @GET("wishlist/{id}")
    suspend fun getWishlistById(
        @Path("id") id: Int,
    ): WishlistResponseItem
}

data class RegisterRequestBody(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
)

data class LoginRequestBody(
    val email: String,
    val password: String,
)

data class ResetPasswordRequest(
    val email: String,
    val password: String,
    val confirmPassword: String,
)

data class WishlistRequest(
    val user_id: Int,
    val smartphone_id: Int,
)

