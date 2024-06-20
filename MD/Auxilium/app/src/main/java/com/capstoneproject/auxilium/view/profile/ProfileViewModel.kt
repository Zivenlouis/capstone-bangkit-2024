package com.capstoneproject.auxilium.view.profile

import androidx.lifecycle.*
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

    init {
        fetchUserProfile()
    }

    fun fetchUserProfile() {
        viewModelScope.launch {
            val userId = userPreference.getUserId().first()
            userId?.let {
                repository.getFlowUserById(it).collect { user ->
                    _userProfile.postValue(user)
                }
            }
        }
    }
}
