package com.capstoneproject.auxilium.ui.forum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneproject.auxilium.response.GetUsersResponseItem
import kotlinx.coroutines.launch

class ForumViewModel(private val repository: ForumRepository) : ViewModel() {

    private val _forumPosts = MutableLiveData<List<ForumPost>>()
    val forumPosts: LiveData<List<ForumPost>> get() = _forumPosts

    private val _likeStatus = MutableLiveData<Map<Int, Boolean>>(mapOf())
    val likeStatus: LiveData<Map<Int, Boolean>> get() = _likeStatus

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    init {
        _likeStatus.value = mapOf()
    }

    fun refreshPosts() {
        viewModelScope.launch {
            try {
                val retrievedPosts = repository.getAllForumPosts()
                _forumPosts.postValue(retrievedPosts)
            } catch (e: Exception) {
                _toastMessage.postValue("Failed to refresh posts: ${e.message}")
            }
        }
    }

    fun likePost(communityId: Int) {
        viewModelScope.launch {
            try {
                val userId = repository.getUserId()
                repository.likeCommunity(userId, communityId)
                updateLikeStatus(communityId, true)
            } catch (e: Exception) {
                _toastMessage.postValue("Failed to like post: ${e.message}")
            }
        }
    }

    fun unlikePost(communityId: Int) {
        viewModelScope.launch {
            try {
                val userId = repository.getUserId()
                repository.unlikeCommunity(userId, communityId)
                updateLikeStatus(communityId, false)
            } catch (e: Exception) {
                _toastMessage.postValue("Failed to unlike post: ${e.message}")
            }
        }
    }

    private fun updateLikeStatus(communityId: Int, isLiked: Boolean) {
        val currentLikes = _likeStatus.value?.toMutableMap() ?: mutableMapOf()
        currentLikes[communityId] = isLiked
        _likeStatus.postValue(currentLikes)

        val currentPosts = _forumPosts.value.orEmpty().toMutableList()
        val updatedPosts = currentPosts.map { post ->
            if (post.communityId == communityId) {
                post.copy(isLiked = isLiked)
            } else {
                post
            }
        }
        _forumPosts.postValue(updatedPosts)
    }

    suspend fun isPostLikedByUser(communityId: Int): Boolean {
        return try {
            val userId = repository.getUserId()
            repository.isPostLikedByUser(userId, communityId)
        } catch (e: Exception) {
            _toastMessage.postValue("Failed to check like status: ${e.message}")
            false
        }
    }

    suspend fun getUserDetails(userId: Int): GetUsersResponseItem? {
        return try {
            repository.getUserDetails(userId)
        } catch (e: Exception) {
            _toastMessage.postValue("Failed to get user details: ${e.message}")
            null
        }
    }

    suspend fun getRepliesCount(communityId: Int): Int {
        return try {
            repository.getRepliesCountByPostId(communityId)
        } catch (e: Exception) {
            _toastMessage.postValue("Failed to get replies count: ${e.message}")
            0
        }
    }

}
