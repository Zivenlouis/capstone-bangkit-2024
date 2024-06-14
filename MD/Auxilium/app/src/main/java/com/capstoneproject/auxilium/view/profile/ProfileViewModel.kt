package com.capstoneproject.auxilium.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.GetUsersResponseItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userPreference: UserPreference,
    private val repository: ProfileRepository,
) : ViewModel() {

    private val _userProfile = MutableLiveData<GetUsersResponseItem?>()
    val userProfile: LiveData<GetUsersResponseItem?> get() = _userProfile

    fun fetchUserProfile() {
        viewModelScope.launch {
            val userId = userPreference.getUserId().first()
            userId?.let {
                val user = repository.getUserById(it)
                _userProfile.postValue(user)
            }
        }
    }
}
