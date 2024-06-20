package com.capstoneproject.auxilium.ui.wishlist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.capstoneproject.auxilium.databinding.FragmentWishlistBinding
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.PhonesResponseItem
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: WishlistViewModel
    private lateinit var viewModelFactory: WishlistViewModelFactory
    private lateinit var userPreference: UserPreference
    private lateinit var wishlistAdapter: WishlistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)

        userPreference = UserPreference.getInstance(requireContext())
        viewModelFactory = WishlistViewModelFactory(userPreference)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[WishlistViewModel::class.java]

        setupViews()
        observeViewModel()

        return binding.root
    }

    private fun setupViews() {
        wishlistAdapter = WishlistAdapter(
            onItemClick = { phoneItem, wishlistId ->
                navigateToDetailActivity(phoneItem, wishlistId)
            },
            onDeleteClickListener = { wishlistId ->
                viewModel.deleteWishlistById(wishlistId)
            }
        )

        binding.rvWishlist.apply {
            adapter = wishlistAdapter
        }
    }

    private fun navigateToDetailActivity(phoneItem: PhonesResponseItem, wishlistId: Int) {
        val intent = Intent(requireContext(), DetailWishlistActivity::class.java).apply {
            putExtra("PHONE_DATA", phoneItem)
            putExtra("WISHLIST_ID", wishlistId)
        }
        startActivityForResult(intent, REQUEST_CODE_DETAIL)
    }

    private fun observeViewModel() {
        viewModel.wishlist.observe(viewLifecycleOwner) { wishlist ->
            if (!wishlist.isNullOrEmpty()) {
                val phonesList = wishlist.mapNotNull { it.second }
                val wishlistItems = wishlist.map { it.first }
                wishlistAdapter.submitList(wishlistItems, phonesList)
                binding.tvEmptyWishlist.visibility = View.GONE
            } else {
                wishlistAdapter.submitList(emptyList(), emptyList())
                binding.tvEmptyWishlist.visibility = View.VISIBLE
            }
        }

        viewModel.deleteSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                lifecycleScope.launch {
                    val userId = getCurrentUserId()
                    viewModel.fetchWishlist(userId)
                    viewModel.deleteSuccess.postValue(false)
                }
            }
        }

        lifecycleScope.launch {
            val userId = getCurrentUserId()
            viewModel.fetchWishlist(userId)
        }
    }

    private suspend fun getCurrentUserId(): Int {
        return userPreference.getUserId().firstOrNull() ?: 100
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        private const val REQUEST_CODE_DETAIL = 100
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_DETAIL && resultCode == Activity.RESULT_OK) {

            lifecycleScope.launch {
                val userId = getCurrentUserId()
                viewModel.fetchWishlist(userId)
            }
        }
    }

}
