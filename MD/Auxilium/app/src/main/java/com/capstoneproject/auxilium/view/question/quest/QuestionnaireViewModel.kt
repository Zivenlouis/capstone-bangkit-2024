package com.capstoneproject.auxilium.view.question.quest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneproject.auxilium.api.UserSurveyRequest
import kotlinx.coroutines.launch

class QuestionnaireViewModel(private val repository: QuestionnaireRepository) : ViewModel() {
    private val _recommendations = MutableLiveData<List<Int>>()
    val recommendations: LiveData<List<Int>> get() = _recommendations

    fun getRecommendations(userSurvey: UserSurveyRequest) {
        viewModelScope.launch {
            val recommendations = repository.getRecommendations(userSurvey)
            _recommendations.value = recommendations
        }
    }
}
