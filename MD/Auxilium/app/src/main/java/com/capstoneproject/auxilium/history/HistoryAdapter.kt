package com.capstoneproject.auxilium.history

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstoneproject.auxilium.databinding.ItemHistoryBinding
import com.capstoneproject.auxilium.ui.home.PhoneItem

class HistoryAdapter(private val context: Context, private val historyList: List<PhoneItem>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyItem = historyList[position]
        holder.bind(historyItem)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(historyItem: PhoneItem) {
            binding.ivPhoneImagesHistory.setImageResource(historyItem.phoneImage)
            binding.tvPhoneNamesHistory.text = historyItem.phoneNames
            binding.tvPhoneOs.text = historyItem.phoneOS

            binding.root.setOnClickListener {
                val intent = Intent(context, DetailHistoryActivity::class.java)
                intent.putExtra("HISTORY_ITEM", historyItem)
                context.startActivity(intent)
            }
        }
    }
}
