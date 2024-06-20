package com.capstoneproject.auxilium.view.newarrivals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailNewArrivalsViewModelFactory(private val repository: DetailNewArrivalsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailNewArrivalsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailNewArrivalsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}