package com.capstoneproject.auxilium.view.additional

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.capstoneproject.auxilium.databinding.ActivitySplashScreenBinding
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.view.MainActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreference = UserPreference.getInstance(this)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            checkTokenAndNavigate()
        }, ANIMATION_DURATION)
    }

    private fun checkTokenAndNavigate() {
        lifecycleScope.launch {
            val token = userPreference.getToken().first()
            val intent = if (token.isNullOrEmpty()) {
                Intent(this@SplashScreenActivity, OnBoardingActivity::class.java)
            } else {
                Intent(this@SplashScreenActivity, MainActivity::class.java)
            }
            startActivity(intent)
            finish()
        }
    }

    companion object {
        private const val ANIMATION_DURATION = 4500L
    }
}
