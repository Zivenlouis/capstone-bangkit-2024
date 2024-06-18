package com.capstoneproject.auxilium.ui.forum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailForumViewModelFactory(private val repository: DetailForumRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailForumViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailForumViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}