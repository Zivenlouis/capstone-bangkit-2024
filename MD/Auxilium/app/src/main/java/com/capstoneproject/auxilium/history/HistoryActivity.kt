package com.capstoneproject.auxilium.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.ActivityHistoryBinding
import com.capstoneproject.auxilium.ui.home.PhoneItem

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var historyAdapter: HistoryAdapter
    private val historyList = listOf(
        PhoneItem(R.drawable.ic_image, "Phone 1", "OS 1"),
        PhoneItem(R.drawable.ic_image, "Phone 2", "OS 2"),
        PhoneItem(R.drawable.ic_image, "Phone 3", "OS 3")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        historyAdapter = HistoryAdapter(this, historyList)
        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        binding.rvHistory.adapter = historyAdapter
    }
}
