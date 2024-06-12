package com.capstoneproject.auxilium.view.newarrivals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.databinding.ItemNewArrivalsBinding
import com.capstoneproject.auxilium.response.PhonesResponseItem

class NewArrivalsAdapter(
    private var phoneList: List<PhonesResponseItem?> = emptyList(),
    private val onItemClick: (PhonesResponseItem) -> Unit
) : RecyclerView.Adapter<NewArrivalsAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: ItemNewArrivalsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(phoneItem: PhonesResponseItem?) {
            binding.apply {
                phoneItem?.let { phone ->
                    Glide.with(itemView.context).load(phone.image).into(ivPhoneImages)
                    tvPhoneNames.text = phone.name
                    tvPhoneOs.text = phone.os
                    root.setOnClickListener { onItemClick(phone) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            ItemNewArrivalsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        phoneList = newList
        notifyDataSetChanged()
    }
}
