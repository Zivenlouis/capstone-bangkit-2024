package com.capstoneproject.auxilium.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(history: History)

    @Query("SELECT * FROM history")
    suspend fun getAllHistory(): List<History>

    @Query("DELETE FROM history WHERE id = :id")
    suspend fun deleteById(id: Int)
}
