package com.capstoneproject.auxilium.ui.forum

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForumPost(
    val profileImage: String,
    val username: String,
    val datePosted: String,
    val description: String,
    val postImage: String,
    var likes: Int,
    val replies: List<Reply>,
    val communityId: Int,
    var isLiked: Boolean
):Parcelable
