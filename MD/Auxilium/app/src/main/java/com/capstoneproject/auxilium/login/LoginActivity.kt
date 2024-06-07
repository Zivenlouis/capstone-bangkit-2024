package com.capstoneproject.auxilium.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.ActivityLoginBinding
import com.capstoneproject.auxilium.view.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(LoginRepository(UserPreference.getInstance(applicationContext)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            val token = viewModel.getSavedToken().firstOrNull()
            if (!token.isNullOrEmpty()) {
                navigateToMainActivity()
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edEmailLogin.text.toString()
            val password = binding.edPasswordLogin.text.toString()
            if (validateEmail(email)) {
                performLogin(email, password)
            }
        }

        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.edEmailLogin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                validateEmail(s.toString())
            }
        })
    }

    private fun validateEmail(email: String): Boolean {
        return if (email.isEmpty()) {
            binding.textInputEmailLogin.error = "Email cannot be empty"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.textInputEmailLogin.error = "Invalid email format"
            false
        } else {
            binding.textInputEmailLogin.error = null
            true
        }
    }

    private fun performLogin(email: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val loginResponse = viewModel.login(email, password)
                if (!loginResponse.accessToken.isNullOrEmpty()) {
                    navigateToMainActivity()
                } else {
                    Log.e("LoginActivity", "Access token is null or blank")
                    Toast.makeText(this@LoginActivity, "Access token is null or blank", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("LoginActivity", "Error during login", e)
                Toast.makeText(this@LoginActivity, "Error during login", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgressBar() {
        findViewById<ProgressBar>(R.id.progress_bar_loading).visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        findViewById<ProgressBar>(R.id.progress_bar_loading).visibility = View.GONE
    }
}
