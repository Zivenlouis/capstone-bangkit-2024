package com.capstoneproject.auxilium.ui.forum

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.databinding.ItemRepliesForumBinding
import com.capstoneproject.auxilium.response.GetRepliesByIdResponseItem

class DetailForumAdapter(private val viewModel: DetailForumViewModel) : RecyclerView.Adapter<DetailForumAdapter.ReplyViewHolder>() {

    private var replies: List<GetRepliesByIdResponseItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepliesForumBinding.inflate(inflater, parent, false)
        return ReplyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReplyViewHolder, position: Int) {
        val reply = replies[position]
        holder.bind(reply)
    }

    override fun getItemCount(): Int {
        return replies.size
    }

    fun updateData(newList: List<GetRepliesByIdResponseItem>) {
        replies = newList
        notifyDataSetChanged()
    }

    inner class ReplyViewHolder(private val binding: ItemRepliesForumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(reply: GetRepliesByIdResponseItem) {
            binding.tvDescriptionReplies.text = reply.comment
            viewModel.userMap.observe(itemView.context as LifecycleOwner) { userMap ->
                userMap[reply.userId]?.let { user ->
                    binding.tvUsernameReplies.text = user.name
                    Glide.with(itemView)
                        .load(user.profileImage)
                        .into(binding.civUserReplies)
                }
            }
        }
    }
}
