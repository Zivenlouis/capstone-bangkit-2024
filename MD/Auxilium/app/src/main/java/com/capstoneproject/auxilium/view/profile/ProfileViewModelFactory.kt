package com.capstoneproject.auxilium.view.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstoneproject.auxilium.datastore.UserPreference

class ProfileViewModelFactory(
    private val userPreference: UserPreference,
    private val repository: ProfileRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(userPreference, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}