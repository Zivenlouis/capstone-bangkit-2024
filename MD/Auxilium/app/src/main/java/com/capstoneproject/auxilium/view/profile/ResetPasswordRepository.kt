package com.capstoneproject.auxilium.view.profile

import com.capstoneproject.auxilium.api.ApiService
import com.capstoneproject.auxilium.api.ResetPasswordRequest
import com.capstoneproject.auxilium.response.ResetPasswordResponse

class ResetPasswordRepository(private val apiService: ApiService) {
    suspend fun resetPassword(userId: Int, request: ResetPasswordRequest): ResetPasswordResponse {
        return apiService.resetPassword(userId, request)
    }
}
