package com.capstoneproject.auxilium.view.profile

import com.capstoneproject.auxilium.api.ApiService
import com.capstoneproject.auxilium.response.GetUsersResponseItem

class ProfileRepository(private val apiService: ApiService) {

    suspend fun getUserById(id: Int): GetUsersResponseItem? {
        val response = apiService.getUserById(id)
        return if (response.isNotEmpty()) response[0] else null
    }
}
