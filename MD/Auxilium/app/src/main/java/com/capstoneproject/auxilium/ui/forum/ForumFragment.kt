package com.capstoneproject.auxilium.ui.forum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.adapter.ForumAdapter
import com.capstoneproject.auxilium.adapter.ForumPost
import com.capstoneproject.auxilium.addpost.AddPostFragment
import com.capstoneproject.auxilium.databinding.FragmentForumBinding

class ForumFragment : Fragment() {

    private var _binding: FragmentForumBinding? = null
    private val binding get() = _binding!!

    private val forumPosts = listOf(
        ForumPost(R.drawable.image_small_circle, "User1", "3h", "Just got my new Samsung A14 and I'm impressed! The battery life is phenomenal, lasting me through a full day of heavy usage with ease.", R.drawable.ic_image, 10, 8),
        ForumPost(R.drawable.image_small_circle, "User2", "5h", "Loving the camera on the new Pixel phone!", R.drawable.ic_image, 20, 15)
        // Add more forum posts here
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForumBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnAddPost.setOnClickListener {
            val addPostFragment = AddPostFragment()
            addPostFragment.show(childFragmentManager, "AddPostFragment")
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvForumPosts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ForumAdapter(forumPosts)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
