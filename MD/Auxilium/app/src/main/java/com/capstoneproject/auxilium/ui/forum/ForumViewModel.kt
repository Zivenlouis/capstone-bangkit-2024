package com.capstoneproject.auxilium.ui.forum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ForumViewModel(private val repository: ForumRepository) : ViewModel() {

    private val _forumPosts = MutableLiveData<List<ForumPost>>()
    val forumPosts: LiveData<List<ForumPost>> get() = _forumPosts

    init {
        refreshPosts()
    }

    fun refreshPosts() {
        viewModelScope.launch {
            val retrievedPosts = repository.getAllForumPosts()
            _forumPosts.postValue(retrievedPosts)
        }
    }
}


class ForumViewModelFactory(private val repository: ForumRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForumViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ForumViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}