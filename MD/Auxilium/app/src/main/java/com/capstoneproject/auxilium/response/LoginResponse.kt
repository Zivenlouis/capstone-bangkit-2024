package com.capstoneproject.auxilium.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("accessToken")
    val accessToken: String? = null,

)
