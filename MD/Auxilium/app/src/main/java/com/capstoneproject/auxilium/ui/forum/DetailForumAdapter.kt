package com.capstoneproject.auxilium.ui.forum

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.databinding.ItemRepliesForumBinding
import com.capstoneproject.auxilium.response.GetRepliesByIdResponseItem

class DetailForumAdapter(private val viewModel: DetailForumViewModel) :
    RecyclerView.Adapter<DetailForumAdapter.ReplyViewHolder>() {

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
        val diffCallback = ReplyDiffCallback(replies, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        replies = newList
        diffResult.dispatchUpdatesTo(this)
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

    private class ReplyDiffCallback(
        private val oldList: List<GetRepliesByIdResponseItem>,
        private val newList: List<GetRepliesByIdResponseItem>
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
