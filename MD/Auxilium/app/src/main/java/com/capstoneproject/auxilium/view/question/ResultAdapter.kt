package com.capstoneproject.auxilium.view.question

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.ItemRecommendedBinding
import com.capstoneproject.auxilium.response.PhonesResponseItem
import com.capstoneproject.auxilium.view.question.detailresult.DetailResultActivity

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    private var phones: List<PhonesResponseItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecommendedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val phone = phones[position]
        holder.bind(phone)
    }

    override fun getItemCount(): Int = phones.size

    fun submitList(newList: List<PhonesResponseItem>) {
        val diffCallback = PhonesDiffCallback(phones, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        phones = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: ItemRecommendedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(phone: PhonesResponseItem) {
            Glide.with(binding.root)
                .load(phone.image)
                .placeholder(R.drawable.ic_image)
                .into(binding.ivPhoneImages)

            binding.tvPhoneNames.text = phone.name
            binding.tvPhoneOs.text = phone.os

            binding.root.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, DetailResultActivity::class.java)
                intent.putExtra("phone", phone)
                context.startActivity(intent)
            }
        }
    }

    private class PhonesDiffCallback(
        private val oldList: List<PhonesResponseItem>,
        private val newList: List<PhonesResponseItem>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
