package com.capstoneproject.auxilium.ui.forum

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.databinding.ItemRepliesForumBinding

class DetailForumAdapter(private val replies: List<Reply>?) :
    RecyclerView.Adapter<DetailForumAdapter.ReplyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepliesForumBinding.inflate(inflater, parent, false)
        return ReplyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReplyViewHolder, position: Int) {
        val reply = replies?.get(position)
        reply?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return replies?.size ?: 0
    }

    inner class ReplyViewHolder(private val binding: ItemRepliesForumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(reply: Reply) {
            binding.tvUsernameReplies.text = reply.replyUsername
            binding.tvDescriptionReplies.text = reply.replyDescription
            Glide.with(itemView)
                .load(reply.replyProfileImage)
                .into(binding.civUserReplies)
        }
    }
}
