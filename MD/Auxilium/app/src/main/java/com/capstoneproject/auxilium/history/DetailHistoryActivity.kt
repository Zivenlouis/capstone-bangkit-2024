package com.capstoneproject.auxilium.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstoneproject.auxilium.databinding.ActivityDetailHistoryBinding

class DetailHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val historyItem = intent.getSerializableExtra("HISTORY_ITEM") as HistoryItem

        binding.ivDetailPhoneImages.setImageResource(historyItem.imageResId)
        binding.tvDetailPhoneNames.text = historyItem.phoneName
        binding.tvDetailPhoneOs.text = historyItem.phoneOS
    }
}
