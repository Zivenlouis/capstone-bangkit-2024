package com.capstoneproject.auxilium.view.newarrivals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.databinding.ItemRecommendedBinding
import com.capstoneproject.auxilium.response.PhonesResponseItem

class FirstNewArrivalsAdapter(
    private var phoneList: List<PhonesResponseItem> = emptyList(),
    private val onItemClick: (PhonesResponseItem) -> Unit
) : RecyclerView.Adapter<FirstNewArrivalsAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemRecommendedBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = phoneList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return phoneList.size
    }

    fun submitList(newList: List<PhonesResponseItem>) {
        val diffCallback = PhonesResponseItemDiffCallback(phoneList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        phoneList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class UserViewHolder(private val binding: ItemRecommendedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(phoneItem: PhonesResponseItem) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(phoneItem.image)
                    .into(ivPhoneImages)
                tvPhoneNames.text = phoneItem.name
                tvPhoneOs.text = phoneItem.os
                root.setOnClickListener { onItemClick(phoneItem) }
            }
        }
    }

    private class PhonesResponseItemDiffCallback(
        private val oldList: List<PhonesResponseItem>,
        private val newList: List<PhonesResponseItem>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
