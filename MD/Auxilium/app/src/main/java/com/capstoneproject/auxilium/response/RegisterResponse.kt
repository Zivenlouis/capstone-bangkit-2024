package com.capstoneproject.auxilium.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("profileImage")
    val profileImage: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null,

    @SerializedName("message")
    val message: String? = null,

    )

