package com.capstoneproject.auxilium.view.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.capstoneproject.auxilium.api.ResetPasswordRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResetPasswordViewModel(private val repository: ResetPasswordRepository) : ViewModel() {

    private val _resetPasswordState = MutableStateFlow<ResetPasswordState>(ResetPasswordState.Idle)
    val resetPasswordState: StateFlow<ResetPasswordState> get() = _resetPasswordState

    fun resetPassword(userId: Int, email: String, password:String, confirmPassword:String) {
        _resetPasswordState.value = ResetPasswordState.Loading
        viewModelScope.launch {
            try {
                val response = repository.resetPassword(userId, ResetPasswordRequest(email, password, confirmPassword))
                _resetPasswordState.value =
                    ResetPasswordState.Success(response.msg ?: "Password reset successfully")
            } catch (e: Exception) {
                _resetPasswordState.value =
                    ResetPasswordState.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }

    sealed class ResetPasswordState {
        data object Idle : ResetPasswordState()
        data object Loading : ResetPasswordState()
        data class Success(val message: String) : ResetPasswordState()
        data class Error(val error: String) : ResetPasswordState()
    }
}


