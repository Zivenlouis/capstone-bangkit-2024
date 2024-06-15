package com.capstoneproject.auxilium.view.profile

import com.capstoneproject.auxilium.api.ApiService
import com.capstoneproject.auxilium.response.GetUsersResponseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProfileRepository(private val apiService: ApiService) {

    fun getFlowUserById(id: Int): Flow<GetUsersResponseItem?> {
        return flow {
            val response = apiService.getUserById(id)
            val user = if (response.isNotEmpty()) response[0] else null
            emit(user)
        }
    }
}