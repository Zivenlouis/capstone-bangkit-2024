package com.capstoneproject.auxilium.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WishlistDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertResult(result: WishlistEntity)

    @Query("SELECT * FROM WishlistTable ORDER BY id ASC")
    fun getAllResults(): LiveData<List<WishlistEntity>>

    @Query("SELECT * FROM WishlistTable WHERE id = :id")
    fun addById(id: Long): WishlistEntity

    @Query("DELETE FROM WishlistTable WHERE id = :id")
    fun deleteById(id: Long)
}