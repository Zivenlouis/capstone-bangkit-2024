package com.capstoneproject.auxilium.ui.forum

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.databinding.ActivityDetailForumBinding

class DetailForumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailForumBinding
    private val viewModel: DetailForumViewModel by viewModels {
        DetailForumViewModelFactory(DetailForumRepository(UserPreference.getInstance(this)))
    }

    private lateinit var adapter: DetailForumAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailForumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val forumPost = intent.getParcelableExtra<ForumPost>("forumPost")
        val userProfile = intent.getStringExtra("userProfileImage")

        if (forumPost != null) {
            viewModel.setForumPost(forumPost)
        } else {
            finish()
            return
        }

        viewModel.forumPost.observe(this) { post ->
            binding.btnBackDetailForum.text = "${post.username}'s post"

            Glide.with(this)
                .load(post.profileImage)
                .into(binding.civForumPostProfile)
            binding.tvForumUsername.text = post.username
            binding.tvForumDescription.text = post.description
            binding.tvForumDateposted.text = post.datePosted
            binding.tvLikesForum.text = post.likes.toString()
            binding.tvReplyForum.text = post.replies.size.toString()
            Glide.with(this)
                .load(post.postImage)
                .into(binding.ivForumPhoto)
            Glide.with(this)
                .load(userProfile)
                .into(binding.civAddReplyProfile)
        }

        binding.btnBackDetailForum.setOnClickListener {
            finish()
        }

        adapter = DetailForumAdapter(viewModel)
        binding.rvForumReplies.adapter = adapter
        binding.rvForumReplies.layoutManager = LinearLayoutManager(this)

        viewModel.repliesList.observe(this) { replies ->
            adapter.updateData(replies)
            replies.forEach { reply ->
                viewModel.fetchUserById(reply.userId ?: 0)
            }
        }

        binding.btnAddReplies.setOnClickListener {
            val communityId = forumPost.communityId
            val reply = binding.edAddReply.text.toString().trim()
            viewModel.addReply(communityId, reply)
            binding.edAddReply.text?.clear()
        }

        viewModel.fetchRepliesByPostId(forumPost.communityId)
    }
}
