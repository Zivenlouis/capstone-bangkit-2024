package com.capstoneproject.auxilium.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey val id: Int,
    val phoneId: Int,
)

@Entity(tableName = "history")
data class PhoneEntity(
    @PrimaryKey val id: Int,
    val brand: String?,
    val name: String?,
    val image: String?,
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,
    val resolution: String?,
    val weight: String?,
    val os: String?,
    val chipset: String?,
    val memory: String?,
    val ram: String?,
    @ColumnInfo(name = "main_camera_1")
    val mainCamera1: String?,
    @ColumnInfo(name = "main_camera_2")
    val mainCamera2: String?,
    @ColumnInfo(name = "main_camera_3")
    val mainCamera3: String?,
    @ColumnInfo(name = "selfie_camera")
    val selfieCamera: String?,
    @ColumnInfo(name = "earphone_jack")
    val earphoneJack: String?,
    val battery: String?,
    val charging: String?,
    val colors: String?,
    val nfc: String?,
    val price: String?,
)
