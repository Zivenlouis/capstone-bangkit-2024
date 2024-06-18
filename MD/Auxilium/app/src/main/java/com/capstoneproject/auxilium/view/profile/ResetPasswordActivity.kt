package com.capstoneproject.auxilium.view.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.databinding.ActivityResetPasswordBinding
import com.capstoneproject.auxilium.datastore.UserPreference
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResetPasswordBinding
    private val userPreference: UserPreference by lazy { UserPreference.getInstance(this) }
    private lateinit var repository: ResetPasswordRepository
    private val viewModel: ResetPasswordViewModel by viewModels {
        ResetPasswordViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val token = userPreference.getToken().firstOrNull()
            val userId = userPreference.getUserId().firstOrNull()
            if (token != null && userId != null) {
                repository = ResetPasswordRepository(ApiConfig.getApiService(token))
                setupUI(userId)
            } else {
                showError(binding.tiEmailReset, "User not found. Please log in.")
                finish()
            }
        }
    }

    private fun setupUI(userId: Int) {
        binding.btnResetPassword.setOnClickListener {
            val email = binding.edEmailReset.text.toString()
            val password = binding.edPassword.text.toString()
            val confirmPassword = binding.edPasswordConfirm.text.toString()

            viewModel.resetPassword(userId, email, password, confirmPassword)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.resetPasswordState.collect { state ->
                when (state) {
                    is ResetPasswordViewModel.ResetPasswordState.Idle -> {
                        // Do nothing
                    }

                    is ResetPasswordViewModel.ResetPasswordState.Loading -> {
                        // Show loading state
                    }

                    is ResetPasswordViewModel.ResetPasswordState.Success -> {
                        showError(binding.tiEmailReset, state.message)
                        // Navigate to another activity or update UI
                    }

                    is ResetPasswordViewModel.ResetPasswordState.Error -> {
                        showError(binding.tiEmailReset, state.error)
                    }
                }
            }
        }
        setupTextWatchers()
    }

    private fun showError(textInputLayout: TextInputLayout, message: String) {
        textInputLayout.error = message
    }

    private fun setupTextWatchers() {
        binding.edEmailReset.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!isValidEmail(s.toString())) {
                    binding.tiEmailReset.error = "Please enter a valid email address."
                } else {
                    binding.tiEmailReset.error = null
                }
            }
        })

        binding.edPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != binding.edPasswordConfirm.text.toString()) {
                    binding.tiPasswordReset.error = "Password and confirm password do not match."
                    binding.tiConfirmPasswordReset.error =
                        "Password and confirm password do not match."
                } else {
                    binding.tiPasswordReset.error = null
                    binding.tiConfirmPasswordReset.error = null
                }
            }
        })

        binding.edPasswordConfirm.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != binding.edPassword.text.toString()) {
                    binding.tiPasswordReset.error = "Password and confirm password do not match."
                    binding.tiConfirmPasswordReset.error =
                        "Password and confirm password do not match."
                } else {
                    binding.tiPasswordReset.error = null
                    binding.tiConfirmPasswordReset.error = null
                }
            }
        })
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}
