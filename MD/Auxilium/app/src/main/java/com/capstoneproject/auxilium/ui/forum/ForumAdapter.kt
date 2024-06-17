package com.capstoneproject.auxilium.ui.forum

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.ItemForumPostBinding

class ForumAdapter(
    private val onItemClick: (ForumPost) -> Unit,
    private val onLikeClick: (ForumPost) -> Unit,
) : RecyclerView.Adapter<ForumAdapter.ForumPostViewHolder>() {

    var forumPosts: List<ForumPost> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumPostViewHolder {
        val binding =
            ItemForumPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForumPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForumPostViewHolder, position: Int) {
        holder.bind(forumPosts[position], onLikeClick)
    }

    override fun getItemCount(): Int {
        return forumPosts.size
    }

    fun updateData(newPosts: List<ForumPost>) {
        val diffCallback = ForumDiffCallback(forumPosts, newPosts)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        forumPosts = newPosts
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ForumPostViewHolder(private val binding: ItemForumPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(forumPost: ForumPost, onLikeClick: (ForumPost) -> Unit) {
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

                ivLikeForum.setImageResource(if (forumPost.isLiked) R.drawable.ic_like_full else R.drawable.ic_like_border)

                forumCardView.setOnClickListener {
                    onItemClick(forumPost)
                }

                ivLikeForum.setOnClickListener {
                    val currentPost = forumPosts[adapterPosition]
                    currentPost.isLiked = !currentPost.isLiked
                    notifyItemChanged(adapterPosition)

                    onLikeClick(currentPost)
                }
            }
        }
    }

    class ForumDiffCallback(
        private val oldList: List<ForumPost>,
        private val newList: List<ForumPost>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].communityId == newList[newItemPosition].communityId
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
