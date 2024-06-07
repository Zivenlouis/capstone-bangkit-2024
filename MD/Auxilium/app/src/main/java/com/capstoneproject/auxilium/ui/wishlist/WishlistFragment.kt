package com.capstoneproject.auxilium.ui.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.FragmentWishlistBinding
import com.capstoneproject.auxilium.ui.home.PhoneItem

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = WishlistFragment()
    }

    private lateinit var viewModel: WishlistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val notificationsViewModel =
            ViewModelProvider(this)[WishlistViewModel::class.java]

        _binding = FragmentWishlistBinding.inflate(inflater, container, false)

        val wishlistItems = listOf(
            PhoneItem(R.drawable.ic_image, "Phone 1", "Some description"),
            PhoneItem(R.drawable.ic_image, "Phone 1", "Some description"),
            PhoneItem(R.drawable.ic_image, "Phone 1", "Some description"),
            PhoneItem(R.drawable.ic_image, "Phone 1", "Some description"),
            PhoneItem(R.drawable.ic_image, "Phone 1", "Some description"),
            PhoneItem(R.drawable.ic_image, "Phone 1", "Some description"),
            PhoneItem(R.drawable.ic_image, "Phone 1", "Some description")// Add more phone items for wishlist
        )

        val wishlistAdapter = WishlistAdapter(wishlistItems)
        binding.rvWishlist.adapter = wishlistAdapter

        val emptyWishlistTextView = binding.tvEmptyWishlist
        if (wishlistItems.isEmpty()) {
            emptyWishlistTextView.visibility = View.VISIBLE
        } else {
            emptyWishlistTextView.visibility = View.GONE
        }


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[WishlistViewModel::class.java]
        // Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
