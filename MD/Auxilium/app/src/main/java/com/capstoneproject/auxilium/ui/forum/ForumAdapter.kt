package com.capstoneproject.auxilium.ui.forum

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.ItemForumPostBinding

class ForumAdapter(
    private val forumPosts: List<ForumPost>,
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

    inner class ForumPostViewHolder(private val binding: ItemForumPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(forumPost: ForumPost) {
            binding.apply {
                civForumPostProfile.setImageResource(forumPost.profileImage)
                tvForumUsername.text = forumPost.username
                tvForumDateposted.text = forumPost.datePosted
                tvForumDescription.text = forumPost.description
                ivForumPhoto.setImageResource(forumPost.postImage)
                tvLikesForum.text = forumPost.likes.toString()
                tvReplyForum.text = forumPost.replies.size.toString()

                ivLikeForum.setImageResource(
                    if (forumPost.isLiked) R.drawable.ic_like_full else R.drawable.ic_like_border
                )

                root.setOnClickListener {
                    onItemClick(forumPost)
                }

                ivLikeForum.setOnClickListener {
                    forumPost.isLiked = !forumPost.isLiked
                    if (forumPost.isLiked) {
                        forumPost.likes += 1
                    } else {
                        forumPost.likes -= 1
                    }
                    tvLikesForum.text = forumPost.likes.toString()
                    ivLikeForum.setImageResource(
                        if (forumPost.isLiked) R.drawable.ic_like_full else R.drawable.ic_like_border
                    )
                }

                ivReplyForum.setOnClickListener {
                    onItemClick(forumPost)
                }
            }
        }
    }
}
