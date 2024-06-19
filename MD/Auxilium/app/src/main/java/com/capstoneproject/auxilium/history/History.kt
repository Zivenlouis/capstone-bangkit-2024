package com.capstoneproject.auxilium.history

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val id1: Int,
    val id2: Int,
    val id3: Int,
    val id4: Int,
    val id5: Int,
    val id6: Int,
    val id7: Int,
    val id8: Int,
    val id9: Int,
    val id10: Int,
)
