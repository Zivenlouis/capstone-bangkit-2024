package com.capstoneproject.auxilium.view.additional

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val imageResource: Int,
    val name: String,
    val role: String,
    val quote: String
) : Parcelable
