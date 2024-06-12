package com.capstoneproject.auxilium.view.question

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.ActivityResultBinding
import com.capstoneproject.auxilium.view.MainActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddHistory.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("selected_tab", R.id.navigation_wishlist)
                startActivity(intent)
                finish()
        }
    }
}