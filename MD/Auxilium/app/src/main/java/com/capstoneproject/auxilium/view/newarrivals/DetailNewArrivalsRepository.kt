package com.capstoneproject.auxilium.view.newarrivals

import com.capstoneproject.auxilium.api.AddRatingRequestBody
import com.capstoneproject.auxilium.api.ApiService
import com.capstoneproject.auxilium.api.WishlistRequest
import com.capstoneproject.auxilium.response.AddRatingsResponse
import com.capstoneproject.auxilium.response.AddWishlistResponse

class DetailNewArrivalsRepository (private val apiService: ApiService) {
    suspend fun addWishlist(userId: Int, smartphoneId: Int): AddWishlistResponse {
        return apiService.addWishlist(WishlistRequest(userId, smartphoneId))
    }

    suspend fun addUserRating(userId: Int, smartphoneId: Int, rating: Char): AddRatingsResponse {
        return apiService.addUserRating(AddRatingRequestBody(userId, smartphoneId, rating))
    }

}