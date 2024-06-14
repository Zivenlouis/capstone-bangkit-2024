package com.capstoneproject.auxilium.response

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.json.JSONException
import org.json.JSONObject

sealed class LikePostResponse {

    data class Success(
		@SerializedName("message")
		val message: String?,
		@SerializedName("like")
		val like: Like,
	) : LikePostResponse()

    data class Error(
		@SerializedName("error")
		val error: String,
	) : LikePostResponse()

    companion object {
        fun fromJson(jsonString: String): LikePostResponse {
            return try {
                val jsonObject = JSONObject(jsonString)
                if (jsonObject.has("like")) {
                    Success(
                        message = jsonObject.optString("message", null),
                        like = Gson().fromJson(
                            jsonObject.getJSONObject("like").toString(),
                            Like::class.java
                        )
                    )
                } else {
                    Error(jsonObject.getString("error"))
                }
            } catch (e: JSONException) {
                Error("Error parsing JSON")
            }
        }
    }
}

data class Like(

	@SerializedName("createdAt")
	val createdAt: String? = null,

	@SerializedName("community_id")
	val communityId: Int? = null,

	@SerializedName("user_id")
	val userId: Int? = null,

	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("updatedAt")
	val updatedAt: String? = null,
)
