package com.capstoneproject.auxilium.ui.forum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class ForumViewModel(private val repository: ForumRepository) : ViewModel() {

    val forumPosts = liveData(Dispatchers.IO) {
        val retrievedPosts = repository.getAllForumPosts()
        emit(retrievedPosts)
    }
}

class ForumViewModelFactory(private val repository:ForumRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForumViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ForumViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}