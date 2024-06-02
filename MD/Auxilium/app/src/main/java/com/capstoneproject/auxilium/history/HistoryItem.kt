package com.capstoneproject.auxilium.history

import java.io.Serializable

data class HistoryItem(
    val imageResId: Int,
    val phoneName: String,
    val phoneOS: String
) : Serializable
