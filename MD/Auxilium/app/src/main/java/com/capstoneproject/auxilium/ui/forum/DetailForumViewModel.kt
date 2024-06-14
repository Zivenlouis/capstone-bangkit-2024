package com.capstoneproject.auxilium.ui.forum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.capstoneproject.auxilium.response.GetRepliesByIdResponseItem
import com.capstoneproject.auxilium.response.GetUsersResponseItem
import kotlinx.coroutines.launch

class DetailForumViewModel(private val repository: DetailForumRepository) : ViewModel() {

    private val _forumPost = MutableLiveData<ForumPost>()
    val forumPost: LiveData<ForumPost>
        get() = _forumPost

    private val _repliesList = MutableLiveData<List<GetRepliesByIdResponseItem>>()
    val repliesList: LiveData<List<GetRepliesByIdResponseItem>>
        get() = _repliesList

    private val _userMap = MutableLiveData<Map<Int, GetUsersResponseItem>>()
    val userMap: LiveData<Map<Int, GetUsersResponseItem>>
        get() = _userMap

    fun setForumPost(post: ForumPost) {
        _forumPost.value = post
    }

    fun fetchRepliesByPostId(postId: Int) {
        viewModelScope.launch {
            try {
                val replies = repository.getRepliesByPostId(postId)
                _repliesList.value = replies
                replies.forEach { reply ->
                    fetchUserById(reply.userId ?: 0)
                }
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }

    fun fetchUserById(userId: Int) {
        viewModelScope.launch {
            try {
                val user = repository.getUserById(userId)
                val currentMap = _userMap.value.orEmpty()
                _userMap.value = currentMap + (userId to user)
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }

    fun addReply(communityId: Int, comment: String) {
        viewModelScope.launch {
            try {
                repository.addPostReply(communityId, comment)
                fetchRepliesByPostId(communityId)
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }
}


class DetailForumViewModelFactory(private val repository: DetailForumRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailForumViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailForumViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
