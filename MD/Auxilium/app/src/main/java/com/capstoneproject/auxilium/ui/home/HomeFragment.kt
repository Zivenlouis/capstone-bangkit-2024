package com.capstoneproject.auxilium.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.databinding.FragmentHomeBinding
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.history.HistoryActivity
import com.capstoneproject.auxilium.view.newarrivals.NewArrivalsActivity
import com.capstoneproject.auxilium.view.question.QuestionnaireActivity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var viewModelFactory: HomeViewModelFactory
    private lateinit var userPreference: UserPreference
    private lateinit var newArrivalsAdapter: ViewNewArrivalsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModelFactory = HomeViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        userPreference = UserPreference.getInstance(requireContext())

        setupViews()
        observeViewModel()
        setupAdapters()

        return root
    }

    private fun setupViews() {
        binding.btnSuperchargeSearch.setOnClickListener {
            startActivity(Intent(requireContext(), QuestionnaireActivity::class.java))
        }

        binding.btnHistory.setOnClickListener {
            startActivity(Intent(requireContext(), HistoryActivity::class.java))
        }

        binding.btnNewArrivals.setOnClickListener {
            startActivity(Intent(requireContext(), NewArrivalsActivity::class.java))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        viewModel.userName.observe(viewLifecycleOwner) { name ->
            Log.d("HomeFragment", "observeViewModel: userName = $name")
            binding.tvUsername.text = name
        }

        viewModel.profileImage.observe(viewLifecycleOwner) { profileImage ->
            Log.d("HomeFragment", "observeViewModel: profileImage = $profileImage")
            Glide.with(this)
                .load(profileImage)
                .into(binding.civProfile)
        }

        lifecycleScope.launch {
            val brand = android.os.Build.BRAND
            val manufacturer = android.os.Build.MANUFACTURER
            val deviceModel = if (brand.isNotEmpty() && manufacturer.isNotEmpty()) {
                "$manufacturer $brand"
            } else {
                "Unknown Device"
            }
            binding.tvUserPhones.text = deviceModel
        }

        lifecycleScope.launch {
            val userId = getCurrentUserId()
            Log.d("HomeFragment", "observeViewModel: userId = $userId")
            viewModel.fetchUserName(userId)
            viewModel.fetchPhones()
            viewModel.fetchWishlist(userId)
        }

        viewModel.phoneList.observe(viewLifecycleOwner) { phones ->
            if (phones != null) {
                newArrivalsAdapter.updateData(phones)
                Log.d("HomeFragment", "observeViewModel: phoneList size = ${phones.size}")
            }
        }

        viewModel.wishlist.observe(viewLifecycleOwner) { wishlistItems ->
            binding.wishlistNumbs.text = "${wishlistItems.size} item(s)"
        }
    }

    private suspend fun getCurrentUserId(): Int {
        val userId = userPreference.getUserId().firstOrNull() ?: 100
        Log.d("HomeFragment", "getCurrentUserId: userId = $userId")
        return userId
    }

    private fun setupAdapters() {
        newArrivalsAdapter = ViewNewArrivalsAdapter()
        binding.rvNewArrivals.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = newArrivalsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
