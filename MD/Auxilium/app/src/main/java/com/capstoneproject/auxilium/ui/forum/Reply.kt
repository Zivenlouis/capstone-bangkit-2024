package com.capstoneproject.auxilium.ui.forum

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reply(
    val id: Int,
    val userId: Int,
    val communityId: Int,
    val comment: String,
    val createdAt: String,
    val updatedAt: String
): Parcelable

