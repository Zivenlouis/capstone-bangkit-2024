package com.capstoneproject.auxilium.ui.forum

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.databinding.FragmentForumBinding
import com.capstoneproject.auxilium.datastore.UserPreference
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ForumFragment : Fragment() {

    private var _binding: FragmentForumBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ForumViewModel
    private lateinit var forumAdapter: ForumAdapter
    private var userProfileImage: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        forumAdapter = ForumAdapter(
            onItemClick = { forumPost ->
                openDetailForumActivity(forumPost)
            },
            onLikeClick = { forumPost ->
                handleLikeClick(forumPost)
            }
        )

        binding.rvForumPosts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvForumPosts.adapter = forumAdapter

        binding.btnAddPost.setOnClickListener {
            val addPostFragment = AddPostFragment()
            addPostFragment.show(childFragmentManager, "AddPostFragment")
        }

        lifecycleScope.launch {
            try {
                val userPreference = UserPreference.getInstance(requireContext())
                val repository = ForumRepository(userPreference)
                viewModel = ViewModelProvider(this@ForumFragment, ForumViewModelFactory(repository))[ForumViewModel::class.java]
                observeViewModel()

                val userId = userPreference.getUserId().firstOrNull()
                userId?.let { it ->
                    val user = viewModel.getUserDetails(it)
                    user?.let {
                        userProfileImage = it.profileImage
                        Glide.with(requireContext())
                            .load(it.profileImage)
                            .into(binding.civForumProfile)
                    }
                }

                viewModel.refreshPosts()
            } catch (e: Exception) {
                // Handle error initializing ViewModel or fetching data
            }
        }

        parentFragmentManager.setFragmentResultListener("postAdded", viewLifecycleOwner) { _, _ ->
            viewModel.refreshPosts()
        }
    }

    private fun observeViewModel() {
        viewModel.forumPosts.observe(viewLifecycleOwner) { posts ->
            forumAdapter.submitList(posts)
        }

        viewModel.likeStatus.observe(viewLifecycleOwner) { likeStatus ->
            likeStatus.forEach { (communityId, isLiked) ->
                forumAdapter.currentList.firstOrNull { it.communityId == communityId }?.let { post ->
                    post.isLiked = isLiked
                    forumAdapter.notifyItemChanged(forumAdapter.currentList.indexOf(post))
                }
            }
        }
    }

    private fun openDetailForumActivity(forumPost: ForumPost) {
        val intent = Intent(requireContext(), DetailForumActivity::class.java).apply {
            putExtra("forumPost", forumPost)
            putExtra("userProfileImage", userProfileImage)
        }
        startActivity(intent)
    }

    private fun handleLikeClick(forumPost: ForumPost) {
        lifecycleScope.launch {
            try {
                val isLiked = viewModel.isPostLikedByUser(forumPost.communityId)
                if (isLiked) {
                    viewModel.unlikePost(forumPost.communityId)
                } else {
                    viewModel.likePost(forumPost.communityId)
                }
            } catch (e: Exception) {
                // Handle error liking/unliking post
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
