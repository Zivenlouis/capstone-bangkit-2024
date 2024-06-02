package com.capstoneproject.auxilium.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstoneproject.auxilium.view.MainActivity
import com.capstoneproject.auxilium.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var intent: Intent
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.tvSignUp.setOnClickListener {
            onSignUp()
        }
    }

    fun onSignUp() {
        intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}
