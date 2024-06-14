package com.capstoneproject.auxilium.ui.forum

import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.api.LikeCommunityRequest
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.GetUsersResponseItem
import com.capstoneproject.auxilium.response.LikePostResponse
import com.capstoneproject.auxilium.response.UnLikePostResponse
import kotlinx.coroutines.flow.firstOrNull
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

class ForumRepository(private val userPreference: UserPreference) {

    suspend fun getAllForumPosts(): List<ForumPost> {
        val token = userPreference.getToken().firstOrNull()
        val apiService = ApiConfig.getApiService(token ?: "")

        if (token.isNullOrEmpty()) {
            return emptyList()
        }

        val response = apiService.getAllForumPosts()
        return response.map { item ->
            val user = getUserDetails(item.userId ?: 0)
            ForumPost(
                profileImage = user?.profileImage ?: "",
                username = user?.name ?: "",
                datePosted = formatTimeDifference(item.createdAt ?: ""),
                description = item.caption ?: "",
                postImage = item.image ?: "",
                likes = item.likeCount ?: 0,
                replies = emptyList(),
                communityId = item.id ?: 0,
                isLiked = false
            )
        }
    }

    suspend fun likeCommunity(userId: Int, communityId: Int): LikePostResponse {
        val token = userPreference.getToken().firstOrNull()
        val apiService = ApiConfig.getApiService(token ?: "")
        val request = LikeCommunityRequest(userId, communityId)
        return apiService.likeCommunity(request)
    }

    suspend fun unlikeCommunity(userId: Int, communityId: Int): UnLikePostResponse {
        val token = userPreference.getToken().firstOrNull()
        val apiService = ApiConfig.getApiService(token ?: "")
        val request = LikeCommunityRequest(userId, communityId)
        return apiService.unlikeCommunity(request)
    }

    private suspend fun getLikesByPostId(communityId: Int): List<GetLikeByPostIdResponseItem> {
        val token = userPreference.getToken().firstOrNull()
        val apiService = ApiConfig.getApiService(token ?: "")
        if (token.isNullOrEmpty()) {
            return emptyList()
        }
        return apiService.getLikesByPostId(communityId)
    }

    suspend fun getUserDetails(userId: Int): GetUsersResponseItem? {
        val token = userPreference.getToken().firstOrNull()
        val apiService = ApiConfig.getApiService(token ?: "")
        if (token.isNullOrEmpty()) {
            return null
        }
        return apiService.getUserById(userId).firstOrNull()
    }

    suspend fun getUserId(): Int {
        return userPreference.getUserId().firstOrNull() ?: throw IllegalStateException("User ID not found")
    }

    suspend fun isPostLikedByUser(userId: Int, communityId: Int): Boolean {
        val likes = getLikesByPostId(communityId)
        return likes.any { it.userId == userId }
    }

    private fun formatTimeDifference(dateString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        val dateTime = LocalDateTime.parse(dateString, formatter)
        val now = LocalDateTime.now()
        val days = ChronoUnit.DAYS.between(dateTime, now)
        val hours = ChronoUnit.HOURS.between(dateTime, now) % 24
        val minutes = ChronoUnit.MINUTES.between(dateTime, now) % 60

        return when {
            days > 0 -> "${days}d ${hours}h ago"
            hours > 0 -> "${hours}h ${minutes}m ago"
            minutes > 0 -> "${minutes}m ago"
            else -> "just now"
        }
    }
}
