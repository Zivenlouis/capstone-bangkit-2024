package com.capstoneproject.auxilium.response

import com.google.gson.annotations.SerializedName

data class CommunityResponse(

	@field:SerializedName("CommunityResponse")
	val communityResponse: List<CommunityResponseItem?>? = null
)

data class CommunityResponseItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("caption")
	val caption: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
