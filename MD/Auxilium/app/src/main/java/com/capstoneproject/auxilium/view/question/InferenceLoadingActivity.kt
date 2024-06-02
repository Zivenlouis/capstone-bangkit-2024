package com.capstoneproject.auxilium.view.question

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.capstoneproject.auxilium.R

class InferenceLoadingActivity : AppCompatActivity() {

    private lateinit var tvCrushingLoading: TextView
    private val handler = Handler()
    private var dotCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inference_loading)

        tvCrushingLoading = findViewById(R.id.tv_crushing_loading)

        simulateLoadingAndSwitchActivity()
    }

    private fun simulateLoadingAndSwitchActivity() {
        handler.postDelayed({
            // Simulate loading time (replace with your actual loading logic)
            val loadingTime = 3000 // milliseconds

            handler.postDelayed({
                // Navigate to ResultActivity after loading time
                val intent = Intent(this, ResultActivity::class.java)
                startActivity(intent)
                finish() // Close InferenceLoadingActivity after navigation
            }, loadingTime.toLong())

            // Start loading animation
            startLoadingAnimation()
        }, 0) // Start after a slight delay for better UX
    }

    private fun startLoadingAnimation() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                val loadingText = StringBuilder("Crunching data just for you")
                for (i in 0 until dotCount) {
                    loadingText.append(".")
                }
                tvCrushingLoading.text = loadingText.toString()
                dotCount = (dotCount + 1) % 4 // Ubah dotCount dari 0 hingga 3
                handler.postDelayed(this, 1000) // Jalankan lagi setelah 1 detik
            }
        }, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null) // Hapus semua callback
    }
}
