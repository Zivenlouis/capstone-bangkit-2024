package com.capstoneproject.auxilium.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstoneproject.auxilium.R

class MainRecAdapter(private val userList: List<PhoneItem>) :
    RecyclerView.Adapter<MainRecAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val phoneImage: ImageView = itemView.findViewById(R.id.iv_phone_images)
        val phoneTitle: TextView = itemView.findViewById(R.id.tv_phone_names)
        val phoneDescription: TextView = itemView.findViewById(R.id.tv_phone_os)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommended_main, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.phoneImage.setImageResource(currentItem.phoneImage)
        holder.phoneTitle.text = currentItem.phoneNames
        holder.phoneDescription.text = currentItem.phoneOS
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}