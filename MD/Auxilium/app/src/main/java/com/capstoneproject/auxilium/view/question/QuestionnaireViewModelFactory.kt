package com.capstoneproject.auxilium.view.question

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QuestionnaireViewModelFactory(private val repository: QuestionnaireRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionnaireViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuestionnaireViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
