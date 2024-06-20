package com.capstoneproject.auxilium.ui.forum

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.databinding.ActivityDetailForumBinding

class DetailForumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailForumBinding
    private lateinit var adapter: DetailForumAdapter
    private val viewModel: DetailForumViewModel by viewModels {
        DetailForumViewModelFactory(DetailForumRepository(UserPreference.getInstance(this)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailForumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val forumPost = intent.getParcelableExtra<ForumPost>("forumPost")
        val userProfile = intent.getStringExtra("userProfileImage")

        if (forumPost == null) {
            finish()
            return
        }

        setupForumPostDetails(forumPost, userProfile)

        binding.ivLikeForum.setOnClickListener {
            if (forumPost.isLiked) {
                viewModel.unlikePost()
            } else {
                viewModel.likePost()
            }
        }

        binding.btnBackDetailForum.setOnClickListener {
            finish()
        }

        setupRecyclerView()

        observeViewModel()

        binding.btnAddReplies.setOnClickListener {
            val communityId = forumPost.communityId
            val reply = binding.edAddReply.text.toString().trim()
            viewModel.addReply(communityId, reply)
            binding.edAddReply.text?.clear()
        }

        viewModel.fetchRepliesByPostId(forumPost.communityId)
    }

    private fun setupForumPostDetails(forumPost: ForumPost, userProfile: String?) {
        binding.btnBackDetailForum.text = "${forumPost.username}'s post"
        binding.tvForumUsername.text = forumPost.username
        binding.tvForumDescription.text = forumPost.description
        binding.tvForumDateposted.text = forumPost.datePosted
        binding.tvLikesForum.text = forumPost.likes.toString()
        binding.tvReplyForum.text = forumPost.replies.size.toString()

        Glide.with(this)
            .load(forumPost.profileImage)
            .into(binding.civForumPostProfile)
        Glide.with(this)
            .load(forumPost.postImage)
            .into(binding.ivForumPhoto)
        Glide.with(this)
            .load(userProfile)
            .into(binding.civAddReplyProfile)

        binding.ivLikeForum.setImageResource(if (forumPost.isLiked) R.drawable.ic_like_full else R.drawable.ic_like_border)
    }

    private fun setupRecyclerView() {
        adapter = DetailForumAdapter(viewModel)
        binding.rvForumReplies.adapter = adapter
        binding.rvForumReplies.layoutManager = LinearLayoutManager(this)
    }

    private fun observeViewModel() {
        viewModel.forumPost.observe(this) { post ->
            binding.tvLikesForum.text = post.likes.toString()
            binding.ivLikeForum.setImageResource(if (post.isLiked) R.drawable.ic_like_full else R.drawable.ic_like_border)
        }

        viewModel.repliesList.observe(this) { replies ->
            adapter.updateData(replies)
            binding.tvReplyForum.text = replies.size.toString()
            replies.forEach { reply ->
                viewModel.fetchUserById(reply.userId ?: 0)
            }
        }
    }
}
