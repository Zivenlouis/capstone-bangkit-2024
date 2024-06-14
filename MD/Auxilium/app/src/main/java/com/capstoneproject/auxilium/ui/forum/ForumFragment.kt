package com.capstoneproject.auxilium.ui.forum

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddPost.setOnClickListener {
            val addPostFragment = AddPostFragment()
            addPostFragment.show(childFragmentManager, "AddPostFragment")
        }

        forumAdapter = ForumAdapter(emptyList()) { forumPost ->
            val intent = Intent(requireContext(), DetailForumActivity::class.java).apply {
                putExtra("forumPost", forumPost)
                putExtra("profileImage", forumPost.profileImage)
            }
            startActivity(intent)
        }

        binding.rvForumPosts.layoutManager = LinearLayoutManager(context)
        binding.rvForumPosts.adapter = forumAdapter

        lifecycleScope.launch {
            val userPreference = UserPreference.getInstance(requireContext())
            val repository = ForumRepository(userPreference)
            viewModel = ForumViewModelFactory(repository).create(ForumViewModel::class.java)
            observeViewModel()

            val userId = userPreference.getUserId().firstOrNull()
            if (userId != null) {
                val user = repository.getUserDetails(userId)
                user?.let {
                    Glide.with(requireContext())
                        .load(it.profileImage)
                        .into(binding.civForumProfile)
                }
            }
        }
        parentFragmentManager.setFragmentResultListener("postAdded", this) { _, _ ->
            viewModel.refreshPosts()
        }

    }

    private fun observeViewModel() {
        viewModel.forumPosts.observe(viewLifecycleOwner) { posts ->
            forumAdapter.updateData(posts)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
