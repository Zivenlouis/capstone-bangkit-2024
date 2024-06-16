package com.capstoneproject.auxilium.history

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhone(phone: HistoryEntity)

    @Query("SELECT * FROM history")
    fun getAllPhones(): LiveData<List<PhoneEntity>>

    @Query("SELECT * FROM history WHERE id = :id")
    suspend fun getPhoneById(id: Int): PhoneEntity?
}

