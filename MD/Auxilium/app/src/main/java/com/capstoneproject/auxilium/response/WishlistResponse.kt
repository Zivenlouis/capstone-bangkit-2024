package com.capstoneproject.auxilium.response

import com.google.gson.annotations.SerializedName

data class WishlistResponse(

	@field:SerializedName("WishlistResponse")
	val wishlistResponse: List<WishlistResponseItem?>? = null
)

data class WishlistResponseItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("smartphone_id")
	val smartphoneId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
