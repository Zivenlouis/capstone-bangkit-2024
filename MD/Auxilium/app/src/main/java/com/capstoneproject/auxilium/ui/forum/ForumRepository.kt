package com.capstoneproject.auxilium.ui.forum

import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.datastore.UserPreference
import kotlinx.coroutines.flow.firstOrNull

class ForumRepository(private val userPreference: UserPreference) {

    suspend fun getAllForumPosts(): List<ForumPost> {
        val token = userPreference.getToken().firstOrNull()
        val apiService = ApiConfig.getApiService(token ?: "")

        if (token.isNullOrEmpty()) {
            return emptyList()
        }

        val response = apiService.getAllForumPosts()
        return response.map { item ->
            item.let {
                ForumPost(
                    profileImage = 0,
                    username = it.userId.toString(),
                    datePosted = it.createdAt ?: "",
                    description = it.caption ?: "",
                    postImage = 0,
                    likes = 0,
                    replies = emptyList(),
                    isLiked = false
                )
            }
        }
    }
}
