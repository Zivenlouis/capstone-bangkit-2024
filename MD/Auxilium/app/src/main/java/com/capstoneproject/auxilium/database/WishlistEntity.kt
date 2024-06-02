package com.capstoneproject.auxilium.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "WishlistTable")
@Parcelize
data class WishlistEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "resultText")
    val resultText: String,

    @ColumnInfo(name = "imageUri")
    val imageUri: String
) : Parcelable