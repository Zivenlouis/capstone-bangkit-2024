package com.capstoneproject.auxilium.ui.wishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.ItemWishlistBinding
import com.capstoneproject.auxilium.response.PhonesResponseItem
import com.capstoneproject.auxilium.response.WishlistResponseItem

class WishlistAdapter(
    private var wishlist: List<WishlistResponseItem> = emptyList(),
    private var phoneList: List<PhonesResponseItem?> = emptyList(),
    private val onItemClick: (PhonesResponseItem, Int) -> Unit,
    private val onDeleteClickListener: (Int) -> Unit,
) : RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>() {

    inner class WishlistViewHolder(val binding: ItemWishlistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(wishlistItem: WishlistResponseItem, phoneItem: PhonesResponseItem?) {
            binding.apply {
                tvPhoneNames.text = phoneItem?.name ?: "Unknown"
                tvPhoneOs.text = phoneItem?.os ?: "Unknown"

                Glide.with(itemView.context)
                    .load(phoneItem?.image)
                    .placeholder(R.drawable.ic_image)
                    .into(binding.ivPhoneImages)

                root.setOnClickListener {
                    phoneItem?.let { phone ->
                        onItemClick(phone, wishlistItem.id!!)
                    }
                }

                btnDeleteWishlist.setOnClickListener {
                    val wishlistIdToDelete = wishlistItem.id
                    onDeleteClickListener(wishlistIdToDelete!!)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val binding =
            ItemWishlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WishlistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        val wishlistItem = wishlist[position]
        val phoneItem = phoneList[position]
        holder.bind(wishlistItem, phoneItem)
    }

    override fun getItemCount(): Int {
        return wishlist.size
    }

    fun submitList(
        newWishlist: List<WishlistResponseItem>,
        newPhoneList: List<PhonesResponseItem?>,
    ) {
        val diffCallback = WishlistDiffCallback(wishlist, phoneList, newWishlist, newPhoneList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        wishlist = newWishlist
        phoneList = newPhoneList
        diffResult.dispatchUpdatesTo(this)
    }

    private class WishlistDiffCallback(
        private val oldWishlist: List<WishlistResponseItem>,
        private val oldPhoneList: List<PhonesResponseItem?>,
        private val newWishlist: List<WishlistResponseItem>,
        private val newPhoneList: List<PhonesResponseItem?>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldWishlist.size

        override fun getNewListSize(): Int = newWishlist.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldWishlist[oldItemPosition].id == newWishlist[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldWishlist[oldItemPosition] == newWishlist[newItemPosition] &&
                    oldPhoneList[oldItemPosition] == newPhoneList[newItemPosition]
        }
    }
}
