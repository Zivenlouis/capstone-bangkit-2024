package com.capstoneproject.auxilium.response

import com.google.gson.annotations.SerializedName
import org.json.JSONException
import org.json.JSONObject

sealed class UnLikePostResponse {

	data class Success(
		@SerializedName("message")
		val message: String?
	) : UnLikePostResponse()

	data class Error(
		@SerializedName("error")
		val error: String
	) : UnLikePostResponse()

	companion object {
		fun fromJson(jsonString: String): UnLikePostResponse {
			return try {
				val jsonObject = JSONObject(jsonString)
				if (jsonObject.has("message")) {
					Success(jsonObject.optString("message", null))
				} else {
					Error(jsonObject.getString("error"))
				}
			} catch (e: JSONException) {
				Error("Error parsing JSON")
			}
		}
	}
}
