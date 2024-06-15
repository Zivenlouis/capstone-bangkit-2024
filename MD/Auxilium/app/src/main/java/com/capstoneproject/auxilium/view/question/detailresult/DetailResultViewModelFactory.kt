package com.capstoneproject.auxilium.view.question.detailresult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailResultViewModelFactory(private val repository: DetailResultRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailResultViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailResultViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}