package com.capstoneproject.auxilium.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstoneproject.auxilium.databinding.ItemForumPostBinding

class ForumAdapter(private val forumPosts: List<ForumPost>) :
    RecyclerView.Adapter<ForumAdapter.ForumPostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumPostViewHolder {
        val binding = ItemForumPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForumPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForumPostViewHolder, position: Int) {
        holder.bind(forumPosts[position])
    }

    override fun getItemCount(): Int = forumPosts.size

    inner class ForumPostViewHolder(private val binding: ItemForumPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(forumPost: ForumPost) {
            binding.civForumPostProfile.setImageResource(forumPost.profileImage)
            binding.tvForumUsername.text = forumPost.username
            binding.tvForumDateposted.text = forumPost.datePosted
            binding.tvForumDescription.text = forumPost.description
            binding.ivForumPhoto.setImageResource(forumPost.postImage)
            binding.tvLikesForum.text = forumPost.likes.toString()
            binding.tvReplyForum.text = forumPost.replies.toString()

            binding.ivLikeForum.setOnClickListener {
                // Handle like action
            }

            binding.ivReplyForum.setOnClickListener {
                // Handle share action
            }
        }
    }
}
