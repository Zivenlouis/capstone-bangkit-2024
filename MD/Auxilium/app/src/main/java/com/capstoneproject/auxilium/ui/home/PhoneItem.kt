package com.capstoneproject.auxilium.ui.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhoneItem(
    val phoneImage: Int,
    val phoneNames: String,
    val phoneOS: String
):Parcelable
