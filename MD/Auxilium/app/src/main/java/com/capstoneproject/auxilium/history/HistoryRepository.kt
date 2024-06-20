package com.capstoneproject.auxilium.history

import android.util.Log
import androidx.annotation.WorkerThread
import com.capstoneproject.auxilium.api.ApiService
import com.capstoneproject.auxilium.response.PhonesResponseItem

class HistoryRepository(private val historyDao: HistoryDao, private val apiService: ApiService) {
    @WorkerThread
    suspend fun insert(history: History) {
        try {
            historyDao.insert(history)
            Log.d("HistoryRepository", "insert: History inserted successfully")
        } catch (e: Exception) {
            Log.e("HistoryRepository", "insert: Error inserting history", e)
        }
    }

    @WorkerThread
    suspend fun getAllHistory(): List<History> {
        return try {
            historyDao.getAllHistory()
        } catch (e: Exception) {
            Log.e("HistoryRepository", "getAllHistory: Error fetching history", e)
            emptyList()
        }
    }

    @WorkerThread
    suspend fun deleteById(id: Int) {
        try {
            historyDao.deleteById(id)
            Log.d("HistoryRepository", "deleteById: History deleted successfully")
        } catch (e: Exception) {
            Log.e("HistoryRepository", "deleteById: Error deleting history", e)
        }
    }

    suspend fun getPhonesByIds(ids: List<Int>): List<PhonesResponseItem> {
        return ids.mapNotNull { id -> apiService.getPhonesById(id).firstOrNull() }
    }
}
