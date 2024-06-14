package com.capstoneproject.auxilium.ui.forum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.datastore.UserPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class AddPostViewModel(private val userPreference: UserPreference) : ViewModel() {

    suspend fun uploadPost(caption: RequestBody, file: File): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val userId = userPreference.getUserId().firstOrNull() ?: return@withContext false
                val token = userPreference.getToken().firstOrNull() ?: return@withContext false

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
                response.isSuccessful
            } catch (e: Exception) {
                e.printStackTrace()
                false
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
