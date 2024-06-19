package com.capstoneproject.auxilium.history

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstoneproject.auxilium.databinding.ActivityHistoryBinding
import com.capstoneproject.auxilium.response.PhonesResponseItem
import com.capstoneproject.auxilium.view.newarrivals.DetailNewArrivalsActivity
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private val historyViewModel: HistoryViewModel by viewModels()
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        historyAdapter = HistoryAdapter(emptyList(), ::onPhoneItemClick, ::onDeleteHistoryClick)
        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        binding.rvHistory.adapter = historyAdapter
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            historyViewModel.repository.collect { it ->
                it ?: return@collect
                val historyList = historyViewModel.getAllHistory()
                historyAdapter.updateHistory(historyList)

                val idsToFetch = historyList.flatMap {
                    listOf(it.id1, it.id2, it.id3, it.id4, it.id5, it.id6, it.id7, it.id8, it.id9, it.id10)
                }
                historyViewModel.fetchPhones(idsToFetch)
            }
        }

        lifecycleScope.launch {
            historyViewModel.phones.collect { phones ->
                historyAdapter.updatePhonesDetails(phones)
            }
        }
    }

    private fun onDeleteHistoryClick(history: History) {
        lifecycleScope.launch {
            historyViewModel.deleteHistory(history)
            val updatedHistoryList = historyViewModel.getAllHistory()
            historyAdapter.updateHistory(updatedHistoryList)
        }
    }


    private fun onPhoneItemClick(phone: PhonesResponseItem) {
        val intent = Intent(this, DetailNewArrivalsActivity::class.java)
        intent.putExtra("PHONE_DATA", phone)
        startActivity(intent)
    }
}
