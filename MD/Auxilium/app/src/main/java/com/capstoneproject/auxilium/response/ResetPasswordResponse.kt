package com.capstoneproject.auxilium.response

import com.google.gson.annotations.SerializedName

data class ResetPasswordResponse(

	@field:SerializedName("msg")
	val msg: String? = null
)
