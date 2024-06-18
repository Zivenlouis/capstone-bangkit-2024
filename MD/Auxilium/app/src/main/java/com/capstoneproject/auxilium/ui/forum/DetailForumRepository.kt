package com.capstoneproject.auxilium.ui.forum

import com.capstoneproject.auxilium.api.AddRepliesRequestBody
import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.api.ApiService
import com.capstoneproject.auxilium.api.LikeCommunityRequest
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.GetRepliesByIdResponseItem
import com.capstoneproject.auxilium.response.GetUsersResponseItem
import kotlinx.coroutines.flow.firstOrNull

class DetailForumRepository(private val userPreference: UserPreference) {

    private var apiService: ApiService? = null

    private suspend fun getApiService(): ApiService {
        if (apiService == null) {
            val token = userPreference.getToken().firstOrNull()
            apiService = ApiConfig.getApiService(token)
        }
        return apiService!!
    }

    suspend fun getRepliesByPostId(postId: Int): List<GetRepliesByIdResponseItem> {
        val service = getApiService()
        return service.getRepliesByPostId(postId)
    }

    suspend fun getUserById(userId: Int): GetUsersResponseItem {
        val service = getApiService()
        val userList = service.getUserById(userId)
        if (userList.isNotEmpty()) {
            return userList[0]
        } else {
            throw IllegalStateException("User with ID $userId not found")
        }
    }

    suspend fun addPostReply(communityId: Int, comment: String) {
        val service = getApiService()
        val userId = userPreference.getUserId().firstOrNull()
        val requestBody = AddRepliesRequestBody(userId!!, communityId, comment)
        service.addPostReplies(requestBody)
    }

    suspend fun getUserId(): Int? {
        return userPreference.getUserId().firstOrNull()
    }

    suspend fun likePost(communityId: Int) {
        val userId = userPreference.getUserId().firstOrNull()
        val service = getApiService()
        service.likeCommunity(LikeCommunityRequest(userId!!, communityId))
    }

    suspend fun unlikePost(communityId: Int) {
        val userId = userPreference.getUserId().firstOrNull()
        val service = getApiService()
        service.unlikeCommunity(LikeCommunityRequest(userId!!, communityId))
    }

}
