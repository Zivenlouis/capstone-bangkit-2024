package com.capstoneproject.auxilium.response

import com.google.gson.annotations.SerializedName

data class LikePostResponse(

	@field:SerializedName("like")
	val like: Like? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Like(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("community_id")
	val communityId: Int? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
