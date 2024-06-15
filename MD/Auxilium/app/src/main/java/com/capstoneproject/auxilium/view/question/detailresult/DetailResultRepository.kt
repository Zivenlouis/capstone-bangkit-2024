package com.capstoneproject.auxilium.view.question.detailresult

import com.capstoneproject.auxilium.api.ApiService
import com.capstoneproject.auxilium.api.WishlistRequest
import com.capstoneproject.auxilium.response.AddWishlistResponse

class DetailResultRepository (private val apiService: ApiService) {
    suspend fun addWishlist(userId: Int, smartphoneId: Int): AddWishlistResponse {
        return apiService.addWishlist(WishlistRequest(userId, smartphoneId))
    }
}