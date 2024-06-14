package com.capstoneproject.auxilium.ui.forum

import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.GetUsersResponseItem
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
                communityId = item.id ?: 0
            )
        }
    }

    suspend fun getUserDetails(userId: Int): GetUsersResponseItem? {
        val token = userPreference.getToken().firstOrNull()
        val apiService = ApiConfig.getApiService(token ?: "")

        if (token.isNullOrEmpty()) {
            return null
        }

        return apiService.getUserById(userId).firstOrNull()
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
