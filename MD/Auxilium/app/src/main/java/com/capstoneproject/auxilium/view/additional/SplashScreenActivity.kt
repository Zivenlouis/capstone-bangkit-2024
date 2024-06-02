package com.capstoneproject.auxilium.view.additional

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.capstoneproject.auxilium.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Animate logo fade-in
        binding.appLogo.alpha = 0f // Initially invisible
        binding.appLogo.animate()
            .alpha(1f) // Fade in to 100% opacity
            .setDuration(2000) // Animation duration (2 seconds)
            .withEndAction {
                // Start LoginActivity after animation
                Handler().postDelayed({
                    val intent = Intent(this, OnBoardingActivity::class.java)
                    startActivity(intent)
                    finish() // Close SplashScreenActivity
                }, 1000) // Delay 1000ms (1 second)
            }
            .start()

        // Optional: Animate text fade-in with a slight delay
        binding.appName.alpha = 0f
        binding.appName.animate()
            .alpha(1f)
            .setDuration(1500) // Slightly shorter duration
            .setStartDelay(500) // Delay start by 500ms
            .start()
    }
}
