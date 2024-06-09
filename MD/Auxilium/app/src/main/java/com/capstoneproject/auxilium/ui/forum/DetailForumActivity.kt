package com.capstoneproject.auxilium.ui.forum

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.databinding.ActivityDetailForumBinding

class DetailForumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailForumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailForumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val forumPost = intent.getParcelableExtra<ForumPost>("forumPost")

        if (forumPost != null) {
            binding.tvForumUsername.text = forumPost.username
            binding.tvForumDateposted.text = forumPost.datePosted
            binding.tvForumDescription.text = forumPost.description
            Glide.with(this)
                .load(forumPost.profileImage)
                .into(binding.civForumPostProfile)
            binding.tvLikesForum?.text = forumPost.likes.toString()
            binding.tvReplyForum?.text = forumPost.replies.size.toString()

            val replyList = forumPost.replies
            val adapter = DetailForumAdapter(replyList)
            binding.rvForumReplies.adapter = adapter
        }
    }
}
