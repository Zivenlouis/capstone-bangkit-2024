package com.capstoneproject.auxilium.di

/*
import android.content.Context
import com.example.storyapps.data.repository.StoryRepository
import com.capstoneproject.auxilium.login.UserPreference
import com.capstoneproject.auxilium.login.getDataStore
import com.example.storyapps.api.ApiConfig
import com.example.storyapps.database.StoryDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getUser().first() }
        val apiService = ApiConfig.getApiService(user?.token)
        val storyDatabase = StoryDatabase.getDatabase(context)
        return StoryRepository.getInstance(apiService,storyDatabase)
    }
}*/
