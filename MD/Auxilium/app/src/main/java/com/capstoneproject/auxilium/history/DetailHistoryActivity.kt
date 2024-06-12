package com.capstoneproject.auxilium.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.ActivityDetailHistoryBinding
import com.capstoneproject.auxilium.ui.home.PhoneItem

class DetailHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val historyItem = intent.getParcelableExtra<PhoneItem>("HISTORY_ITEM")

        if (historyItem != null) {
            binding.ivDetailPhoneImages.setImageResource(historyItem.phoneImage)
            binding.tvDetailPhoneNames.text = historyItem.phoneNames
            binding.tvDetailPhoneOs.text = historyItem.phoneOS
        }

        val recommendedList = listOf(
            PhoneItem(R.drawable.ic_image, "Recommended Phone 1", "Android"),
            PhoneItem(R.drawable.ic_image, "Recommended Phone 2", "iOS"),
            PhoneItem(R.drawable.ic_image, "Recommended Phone 3", "Android")
        )

        val adapter = DetailHistoryAdapter(this, recommendedList)
        binding.rvRecommended.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecommended.adapter = adapter
    }
}
