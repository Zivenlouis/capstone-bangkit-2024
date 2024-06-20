package com.capstoneproject.auxilium.view.newarrivals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewArrivalsViewModelFactory(private val repository: NewArrivalsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewArrivalsViewModel::class.java)) {
            return NewArrivalsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
