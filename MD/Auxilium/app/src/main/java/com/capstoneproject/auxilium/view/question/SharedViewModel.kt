package com.capstoneproject.auxilium.view.question

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.capstoneproject.auxilium.api.UserSurveyRequest

class SharedViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    companion object {
        private const val QUESTION_1_KEY = "question1Response"
        private const val QUESTION_2_KEY = "question2Response"
        private const val QUESTION_3_KEY = "question3Response"
        private const val QUESTION_4_KEY = "question4Response"
        private const val QUESTION_5_KEY = "question5Response"
        private const val QUESTION_6_KEY = "question6Response"
        private const val QUESTION_7_KEY = "question7Response"
        private const val TAG = "SharedViewModel"
    }

    init {
        if (!savedStateHandle.contains(QUESTION_1_KEY)) {
            savedStateHandle[QUESTION_1_KEY] = 0
        }
        if (!savedStateHandle.contains(QUESTION_2_KEY)) {
            savedStateHandle[QUESTION_2_KEY] = 0
        }
        if (!savedStateHandle.contains(QUESTION_3_KEY)) {
            savedStateHandle[QUESTION_3_KEY] = 0
        }
        if (!savedStateHandle.contains(QUESTION_4_KEY)) {
            savedStateHandle[QUESTION_4_KEY] = 0
        }
        if (!savedStateHandle.contains(QUESTION_5_KEY)) {
            savedStateHandle[QUESTION_5_KEY] = 0
        }
        if (!savedStateHandle.contains(QUESTION_6_KEY)) {
            savedStateHandle[QUESTION_6_KEY] = 0
        }
        if (!savedStateHandle.contains(QUESTION_7_KEY)) {
            savedStateHandle[QUESTION_7_KEY] = ""
        }
    }

    fun setQuestion1Response(response: Int) {
        savedStateHandle[QUESTION_1_KEY] = response
        Log.d(TAG, "Question 1 Response set to $response")
    }

    fun setQuestion2Response(response: Int) {
        savedStateHandle[QUESTION_2_KEY] = response
        Log.d(TAG, "Question 2 Response set to $response")
    }

    fun setQuestion3Response(response: Int) {
        savedStateHandle[QUESTION_3_KEY] = response
        Log.d(TAG, "Question 3 Response set to $response")
    }

    fun setQuestion4Response(response: Int) {
        savedStateHandle[QUESTION_4_KEY] = response
        Log.d(TAG, "Question 4 Response set to $response")
    }

    fun setQuestion5Response(response: Int) {
        savedStateHandle[QUESTION_5_KEY] = response
        Log.d(TAG, "Question 5 Response set to $response")
    }

    fun setQuestion6Response(response: Int) {
        savedStateHandle[QUESTION_6_KEY] = response
        Log.d(TAG, "Question 6 Response set to $response")
    }

    fun setQuestion7Response(response: String) {
        savedStateHandle[QUESTION_7_KEY] = response
        Log.d(TAG, "Question 7 Response set to $response")
    }

    fun getQuestion1Response(): Int {
        return savedStateHandle.get<Int>(QUESTION_1_KEY) ?: 0
    }

    fun getQuestion2Response(): Int {
        return savedStateHandle.get<Int>(QUESTION_2_KEY) ?: 0
    }

    fun getQuestion3Response(): Int {
        return savedStateHandle.get<Int>(QUESTION_3_KEY) ?: 0
    }

    fun getQuestion4Response(): Int {
        return savedStateHandle.get<Int>(QUESTION_4_KEY) ?: 0
    }

    fun getQuestion5Response(): Int {
        return savedStateHandle.get<Int>(QUESTION_5_KEY) ?: 0
    }

    fun getQuestion6Response(): Int {
        return savedStateHandle.get<Int>(QUESTION_6_KEY) ?: 0
    }

    fun getQuestion7Response(): String {
        return savedStateHandle.get<String>(QUESTION_7_KEY) ?: ""
    }

    fun getAllResponses(): UserSurveyRequest {
        val responses = mutableListOf<Any>()
        responses.add(getQuestion1Response())
        responses.add(getQuestion2Response())
        responses.add(getQuestion3Response())
        responses.add(getQuestion4Response())
        responses.add(getQuestion5Response())
        responses.add(getQuestion6Response())
        responses.add(getQuestion7Response())
        return UserSurveyRequest(responses)
    }
}
