package com.capstoneproject.auxilium.view.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.GetUsersResponseItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userPreference: UserPreference,
    private val repository: ProfileRepository,
) : ViewModel() {

    private val _userProfile = MutableStateFlow<GetUsersResponseItem?>(null)
    val userProfile: StateFlow<GetUsersResponseItem?> get() = _userProfile

    fun fetchUserProfile() {
        viewModelScope.launch {
            val userId = userPreference.getUserId().first()
            userId?.let {
                val user = repository.getUserById(it)
                _userProfile.value = user
            }
        }
    }
}

