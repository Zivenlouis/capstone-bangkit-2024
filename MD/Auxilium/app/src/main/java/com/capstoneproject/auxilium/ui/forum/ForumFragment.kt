package com.capstoneproject.auxilium.ui.forum

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstoneproject.auxilium.addpost.AddPostFragment
import com.capstoneproject.auxilium.databinding.FragmentForumBinding
import com.capstoneproject.auxilium.login.UserPreference
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ForumFragment : Fragment() {

    private var _binding: FragmentForumBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ForumViewModel

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

        binding.rvForumPosts.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launch {
            val userPreference = UserPreference.getInstance(requireContext())
            userPreference.getToken().firstOrNull()
            val repository = ForumRepository(userPreference)
            viewModel = ForumViewModelFactory(repository).create(ForumViewModel::class.java)
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        viewModel.forumPosts.observe(viewLifecycleOwner) { posts ->
            binding.rvForumPosts.adapter = ForumAdapter(posts) { forumPost ->
                val intent = Intent(requireContext(), DetailForumActivity::class.java).apply {
                    putExtra("forumPost", forumPost)
                }
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
