package com.capstoneproject.auxilium.ui.forum

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.ItemForumPostBinding

class ForumAdapter(
    private var forumPosts: List<ForumPost>,
    private val onItemClick: (ForumPost) -> Unit
) : RecyclerView.Adapter<ForumAdapter.ForumPostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumPostViewHolder {
        val binding = ItemForumPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForumPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForumPostViewHolder, position: Int) {
        holder.bind(forumPosts[position])
    }

    override fun getItemCount(): Int = forumPosts.size

    fun updateData(newForumPosts: List<ForumPost>) {
        forumPosts = newForumPosts
        notifyDataSetChanged()
    }

    inner class ForumPostViewHolder(private val binding: ItemForumPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(forumPost: ForumPost) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(forumPost.profileImage)
                    .placeholder(R.drawable.ic_user)
                    .error(R.drawable.ic_user)
                    .into(civForumPostProfile)
                tvForumUsername.text = forumPost.username
                tvForumDateposted.text = forumPost.datePosted
                tvForumDescription.text = forumPost.description
                Glide.with(itemView.context)
                    .load(forumPost.postImage)
                    .placeholder(R.drawable.ic_image)
                    .error(R.drawable.ic_image)
                    .into(ivForumPhoto)

                tvLikesForum.text = forumPost.likes.toString()
                tvReplyForum.text = forumPost.replies.size.toString()

                root.setOnClickListener {
                    onItemClick(forumPost)
                }

                ivReplyForum.setOnClickListener {
                    onItemClick(forumPost)
                }
            }
        }
    }
}
