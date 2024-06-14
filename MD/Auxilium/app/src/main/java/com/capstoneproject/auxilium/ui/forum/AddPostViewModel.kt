package com.capstoneproject.auxilium.ui.forum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.datastore.UserPreference
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class AddPostViewModel(private val userPreference: UserPreference) : ViewModel() {

    suspend fun uploadPost(caption: RequestBody, file: File) {
        viewModelScope.launch {
            try {
                val userId = userPreference.getUserId().firstOrNull() ?: return@launch

                val token = userPreference.getToken().firstOrNull() ?: return@launch

                val filePart = MultipartBody.Part.createFormData(
                    "file",
                    file.name,
                    file.asRequestBody("image/*".toMediaTypeOrNull())
                )

                val response = ApiConfig.getApiService(token).createPost(
                    userId,
                    caption,
                    filePart
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

class AddPostViewModelFactory(private val userPreference: UserPreference) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddPostViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddPostViewModel(userPreference) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}