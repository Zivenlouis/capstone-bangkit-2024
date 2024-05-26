package com.capstoneproject.auxilium.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.capstoneproject.auxilium.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var intent: Intent
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.tvSignIn.setOnClickListener {
            onSignIn(it)
        }
    }

    fun onSignIn(view: View) {
        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}