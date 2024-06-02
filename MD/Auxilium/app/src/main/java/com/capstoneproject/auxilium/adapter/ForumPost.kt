package com.capstoneproject.auxilium.adapter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForumPost(
    val profileImage: Int,
    val username: String,
    val datePosted: String,
    val description: String,
    val postImage: Int,
    val likes: Int,
    val replies: Int
):Parcelable
