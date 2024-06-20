package com.capstoneproject.auxilium.ui.forum

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.R
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
        savedInstanceState: Bundle?,
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

        binding.btnBackForum.setOnClickListener {
            findNavController().navigate(R.id.action_forumFragment_to_homeFragment)
        }

        initializeViewModelAndFetchData()

        parentFragmentManager.setFragmentResultListener("postAdded", viewLifecycleOwner) { _, _ ->
            lifecycleScope.launch {
                viewModel.refreshPosts()
                val addPostFragment = childFragmentManager.findFragmentByTag("AddPostFragment")
                if (addPostFragment != null && addPostFragment is AddPostFragment) {
                    addPostFragment.dismiss()
                }
            }
        }
    }

    private fun initializeViewModelAndFetchData() {
        lifecycleScope.launch {
            try {
                val userPreference = UserPreference.getInstance(requireContext())
                val repository = ForumRepository(userPreference)
                viewModel = ViewModelProvider(
                    this@ForumFragment,
                    ForumViewModelFactory(repository)
                )[ForumViewModel::class.java]
                observeViewModel()

                val userId = userPreference.getUserId().firstOrNull()
                userId?.let {
                    val user = viewModel.getUserDetails(userId)
                    user?.let { userDetails ->
                        userProfileImage = userDetails.profileImage
                        Glide.with(requireContext())
                            .load(userDetails.profileImage)
                            .into(binding.civForumProfile)
                    }
                }

                viewModel.refreshPosts()
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Error initializing ViewModel or fetching data: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        initializeViewModelAndFetchData()
    }

    private fun observeViewModel() {
        viewModel.forumPosts.observe(viewLifecycleOwner) { posts ->
            forumAdapter.updateData(posts)
        }

        viewModel.likeStatus.observe(viewLifecycleOwner) { likeStatus ->
            likeStatus.forEach { (communityId, isLiked) ->
                forumAdapter.forumPosts.firstOrNull { it.communityId == communityId }?.let { post ->
                    post.isLiked = isLiked
                    forumAdapter.notifyItemChanged(forumAdapter.forumPosts.indexOf(post))
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
                Toast.makeText(
                    requireContext(),
                    "Error liking/unliking post: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
