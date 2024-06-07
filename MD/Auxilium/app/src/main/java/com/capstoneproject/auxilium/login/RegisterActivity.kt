package com.capstoneproject.auxilium.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.ActivityRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels {
        RegisterViewModelFactory(RegisterRepository(UserPreference.getInstance(applicationContext)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val username = binding.edUsernameRegister.text.toString()
            val email = binding.edEmailRegister.text.toString()
            val password = binding.edPasswordRegister.text.toString()
            val confirmPassword = binding.edConfirmPasswordRegister.text.toString()

            if (!isValidEmail(email)) {
                binding.textInputEmailLogin.error = "Please enter a valid email address."
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                binding.textInputPasswordLogin.error = "Password and confirm password do not match."
                binding.textInputConfirmPassword.error = "Password and confirm password do not match."
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    viewModel.register(username, email, password, confirmPassword)
                    Toast.makeText(this@RegisterActivity, "Registration successful", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this@RegisterActivity, "Registration failed: ${e.message}", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
        }

        binding.tvSignIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Add TextWatchers to validate the input while typing
        setupTextWatchers()
    }

    private fun setupTextWatchers() {
        binding.edEmailRegister.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!isValidEmail(s.toString())) {
                    binding.textInputEmailLogin.error = "Please enter a valid email address."
                } else {
                    binding.textInputEmailLogin.error = null
                }
            }
        })

        binding.edPasswordRegister.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != binding.edConfirmPasswordRegister.text.toString()) {
                    binding.textInputPasswordLogin.error = "Password and confirm password do not match."
                    binding.textInputConfirmPassword.error = "Password and confirm password do not match."
                } else {
                    binding.textInputPasswordLogin.error = null
                    binding.textInputConfirmPassword.error = null
                }
            }
        })

        binding.edConfirmPasswordRegister.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != binding.edPasswordRegister.text.toString()) {
                    binding.textInputPasswordLogin.error = "Password and confirm password do not match."
                    binding.textInputConfirmPassword.error = "Password and confirm password do not match."
                } else {
                    binding.textInputPasswordLogin.error = null
                    binding.textInputConfirmPassword.error = null
                }
            }
        })
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showToast(message: String) {
        Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgressBar() {
        findViewById<ProgressBar>(R.id.progress_bar_loading).visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        findViewById<ProgressBar>(R.id.progress_bar_loading).visibility = View.GONE
    }
}
