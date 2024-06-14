package com.capstoneproject.auxilium.ui.forum

import com.google.gson.annotations.SerializedName

data class GetLikeByPostIdResponse(

	@field:SerializedName("GetLikeByPostIdResponse")
	val getLikeByPostIdResponse: List<GetLikeByPostIdResponseItem?>? = null
)

data class GetLikeByPostIdResponseItem(

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
