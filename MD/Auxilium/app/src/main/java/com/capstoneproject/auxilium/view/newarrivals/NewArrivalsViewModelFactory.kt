package com.capstoneproject.auxilium.view.newarrivals

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstoneproject.auxilium.datastore.UserPreference

class NewArrivalsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewArrivalsViewModel::class.java)) {
            val userPreference = UserPreference.getInstance(context)
            return NewArrivalsViewModel(userPreference) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}