package com.capstoneproject.auxilium.ui.forum

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reply(
    val replyProfileImage: String,
    val replyUsername: String,
    val replyDescription: String
): Parcelable

