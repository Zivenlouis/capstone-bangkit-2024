package com.capstoneproject.auxilium.response

import com.google.gson.annotations.SerializedName

data class GetRepliesByIdResponse(

	@field:SerializedName("GetRepliesByIdResponse")
	val getRepliesByIdResponse: List<GetRepliesByIdResponseItem?>? = null
)

data class GetRepliesByIdResponseItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("community_id")
	val communityId: Int? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("comment")
	val comment: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
