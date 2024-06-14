package com.capstoneproject.auxilium.view.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.EditProfileResponse
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class EditProfileViewModel(context: Context) : ViewModel() {

    private val userPreference = UserPreference.getInstance(context)

    private val _editProfileResponse = MutableLiveData<EditProfileResponse>()
    val editProfileResponse: LiveData<EditProfileResponse> get() = _editProfileResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun editProfile(name: RequestBody, email: RequestBody, profileImage: MultipartBody.Part?) {
        viewModelScope.launch {
            val token = userPreference.getToken().firstOrNull()
            val userId = userPreference.getUserId().firstOrNull()

            if (token != null && userId != null) {
                try {
                    val apiService = ApiConfig.getApiService(token)
                    val response: EditProfileResponse = apiService.editProfile(userId, name, email, profileImage)
                    _editProfileResponse.postValue(response)
                } catch (e: Exception) {
                    _error.postValue(e.message)
                }
            } else {
                _error.postValue("Token or User ID is null")
            }
        }
    }
}
