package com.capstoneproject.auxilium.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.response.PhonesResponseItem

class MainRecAdapter(
    private val onItemClick: (PhonesResponseItem) -> Unit,
) : ListAdapter<PhonesResponseItem, MainRecAdapter.ViewHolder>(PhonesDiffCallback()) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val phoneImage: ImageView = itemView.findViewById(R.id.iv_phone_images)
        val phoneTitle: TextView = itemView.findViewById(R.id.tv_phone_names)
        val phoneDescription: TextView = itemView.findViewById(R.id.tv_phone_os)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommended_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        Glide.with(holder.itemView.context).load(currentItem.image).into(holder.phoneImage)
        holder.phoneTitle.text = currentItem.name
        holder.phoneDescription.text = currentItem.os
        holder.itemView.setOnClickListener { onItemClick(currentItem) }
    }

    class PhonesDiffCallback : DiffUtil.ItemCallback<PhonesResponseItem>() {
        override fun areItemsTheSame(
            oldItem: PhonesResponseItem,
            newItem: PhonesResponseItem,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PhonesResponseItem,
            newItem: PhonesResponseItem,
        ): Boolean {
            return oldItem == newItem
        }
    }
}

