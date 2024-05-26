package com.capstoneproject.auxilium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstoneproject.auxilium.databinding.FragmentPostingBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PostingFragment : Fragment() {

    private var _binding: FragmentPostingBinding? = null
    private val binding get() = _binding!!

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    companion object {
        fun newInstance() = PostingFragment()
    }

    private lateinit var viewModel: PostingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostingBinding.inflate(inflater, container, false)
        val view = binding.root

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehavior.isHideable = true // Make bottom sheet hideable
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN // Set initial state to hidden
        bottomSheetBehavior.peekHeight = 100

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[PostingViewModel::class.java]
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}