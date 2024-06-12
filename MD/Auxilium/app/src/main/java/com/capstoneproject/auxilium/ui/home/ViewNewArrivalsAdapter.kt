package com.capstoneproject.auxilium.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.databinding.ItemHomeNewArrivalsBinding
import com.capstoneproject.auxilium.response.PhonesResponseItem

class ViewNewArrivalsAdapter(private var phoneList: List<PhonesResponseItem?> = emptyList()) :
    RecyclerView.Adapter<ViewNewArrivalsAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: ItemHomeNewArrivalsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(phoneItem: PhonesResponseItem?) {
            binding.apply {
                phoneItem?.let {
                    Glide.with(itemView.context).load(it.image).into(ivPhoneImages)
                    tvPhoneNames.text = it.name
                    tvPhoneOs.text = it.os
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            ItemHomeNewArrivalsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = phoneList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return phoneList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(updatedList: List<PhonesResponseItem>) {
        phoneList = updatedList
        notifyDataSetChanged()
    }
}
