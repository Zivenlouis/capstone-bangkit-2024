package com.capstoneproject.auxilium.history

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstoneproject.auxilium.databinding.ItemRecommendedBinding
import com.capstoneproject.auxilium.ui.home.PhoneItem

class DetailHistoryAdapter(
    private val context: Context,
    private val recommendedList: List<PhoneItem>
) : RecyclerView.Adapter<DetailHistoryAdapter.DetailHistoryViewHolder>() {

    inner class DetailHistoryViewHolder(private val binding: ItemRecommendedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PhoneItem) {
            binding.ivPhoneImages.setImageResource(item.phoneImage)
            binding.tvPhoneNames.text = item.phoneNames
            binding.tvPhoneOs.text = item.phoneOS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHistoryViewHolder {
        val binding = ItemRecommendedBinding.inflate(LayoutInflater.from(context), parent, false)
        return DetailHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailHistoryViewHolder, position: Int) {
        holder.bind(recommendedList[position])
    }

    override fun getItemCount(): Int = recommendedList.size
}
