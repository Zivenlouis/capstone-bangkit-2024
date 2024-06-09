package com.capstoneproject.auxilium.ui.forum

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForumPost(
    val profileImage: Int,
    val username: String,
    val datePosted: String,
    val description: String,
    val postImage: Int,
    var likes: Int,
    val replies: List<Reply>,
    var isLiked: Boolean = false
):Parcelable
