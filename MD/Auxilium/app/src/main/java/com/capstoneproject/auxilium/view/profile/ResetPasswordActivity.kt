package com.capstoneproject.auxilium.view.profile

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun showToast(message: String) {
        Toast.makeText(this@ResetPasswordActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgressBar() {
        findViewById<ProgressBar>(R.id.progress_bar_loading).visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        findViewById<ProgressBar>(R.id.progress_bar_loading).visibility = View.GONE
    }
}