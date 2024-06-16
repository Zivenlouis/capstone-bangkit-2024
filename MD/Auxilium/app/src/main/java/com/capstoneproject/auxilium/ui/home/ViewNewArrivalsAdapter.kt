package com.capstoneproject.auxilium.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.databinding.ItemHomeNewArrivalsBinding
import com.capstoneproject.auxilium.response.PhonesResponseItem
import kotlin.math.min

class ViewNewArrivalsAdapter(private var phoneList: List<PhonesResponseItem> = emptyList()) :
    RecyclerView.Adapter<ViewNewArrivalsAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: ItemHomeNewArrivalsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(phoneItem: PhonesResponseItem) {
            binding.apply {
                Glide.with(itemView.context).load(phoneItem.image).into(ivPhoneImages)
                tvPhoneNames.text = phoneItem.name
                tvPhoneOs.text = phoneItem.os
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemHomeNewArrivalsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = phoneList[position]
        holder.bind(currentItem)
    }

        override fun getItemCount(): Int {
            return min(phoneList.size, 7)
        }

    fun updateData(updatedList: List<PhonesResponseItem>) {
        val diffCallback = PhonesDiffCallback(phoneList, updatedList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        phoneList = updatedList
        diffResult.dispatchUpdatesTo(this)
    }

    private class PhonesDiffCallback(
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
