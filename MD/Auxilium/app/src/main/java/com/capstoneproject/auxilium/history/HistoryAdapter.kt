package com.capstoneproject.auxilium.history

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.ItemHistoryMainBinding
import com.capstoneproject.auxilium.response.PhonesResponseItem

class HistoryAdapter(
    private var historyList: List<History>,
    private val onItemClick: (PhonesResponseItem) -> Unit,
    private val onDeleteClick: (History) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private lateinit var context: Context
    private var phoneDetails: List<PhonesResponseItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        context = parent.context
        val binding = ItemHistoryMainBinding.inflate(LayoutInflater.from(context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(historyList[position])
    }

    override fun getItemCount(): Int = historyList.size

    inner class HistoryViewHolder(private val binding: ItemHistoryMainBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.cvPhoneItem1.setOnClickListener(this)
            binding.cvPhoneItem2.setOnClickListener(this)
            binding.cvPhoneItem3.setOnClickListener(this)
            binding.cvPhoneItem4.setOnClickListener(this)
            binding.cvPhoneItem5.setOnClickListener(this)
            binding.cvPhoneItem6.setOnClickListener(this)
            binding.cvPhoneItem7.setOnClickListener(this)
            binding.cvPhoneItem8.setOnClickListener(this)
            binding.cvPhoneItem9.setOnClickListener(this)
            binding.cvPhoneItem10.setOnClickListener(this)

            binding.btnDeleteHistory.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDeleteClick(historyList[position])
                }
            }
        }

        fun bind(history: History) {
            binding.tvHistoryItemNumber.text = "History ${history.id}"

            bindPhoneDetails(binding.tvPhoneNames1, binding.tvPhoneOs1, binding.ivPhoneImages1, getPhoneDetailById(history.id1))
            bindPhoneDetails(binding.tvPhoneNames2, binding.tvPhoneOs2, binding.ivPhoneImages2, getPhoneDetailById(history.id2))
            bindPhoneDetails(binding.tvPhoneNames3, binding.tvPhoneOs3, binding.ivPhoneImages3, getPhoneDetailById(history.id3))
            bindPhoneDetails(binding.tvPhoneNames4, binding.tvPhoneOs4, binding.ivPhoneImages4, getPhoneDetailById(history.id4))
            bindPhoneDetails(binding.tvPhoneNames5, binding.tvPhoneOs5, binding.ivPhoneImages5, getPhoneDetailById(history.id5))
            bindPhoneDetails(binding.tvPhoneNames6, binding.tvPhoneOs6, binding.ivPhoneImages6, getPhoneDetailById(history.id6))
            bindPhoneDetails(binding.tvPhoneNames7, binding.tvPhoneOs7, binding.ivPhoneImages7, getPhoneDetailById(history.id7))
            bindPhoneDetails(binding.tvPhoneNames8, binding.tvPhoneOs8, binding.ivPhoneImages8, getPhoneDetailById(history.id8))
            bindPhoneDetails(binding.tvPhoneNames9, binding.tvPhoneOs9, binding.ivPhoneImages9, getPhoneDetailById(history.id9))
            bindPhoneDetails(binding.tvPhoneNames10, binding.tvPhoneOs10, binding.ivPhoneImages10, getPhoneDetailById(history.id10))
        }

        override fun onClick(v: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val phoneId = getPhoneIdAtPosition(v.id)
                val phoneDetail = getPhoneDetailById(phoneId)
                phoneDetail?.let {
                    onItemClick(it)
                }
            }
        }

        private fun getPhoneIdAtPosition(viewId: Int): Int {
            return when (viewId) {
                R.id.cv_phone_item1 -> historyList[adapterPosition].id1
                R.id.cv_phone_item2 -> historyList[adapterPosition].id2
                R.id.cv_phone_item3 -> historyList[adapterPosition].id3
                R.id.cv_phone_item4 -> historyList[adapterPosition].id4
                R.id.cv_phone_item5 -> historyList[adapterPosition].id5
                R.id.cv_phone_item6 -> historyList[adapterPosition].id6
                R.id.cv_phone_item7 -> historyList[adapterPosition].id7
                R.id.cv_phone_item8 -> historyList[adapterPosition].id8
                R.id.cv_phone_item9 -> historyList[adapterPosition].id9
                R.id.cv_phone_item10 -> historyList[adapterPosition].id10
                else -> -1
            }
        }

        private fun bindPhoneDetails(tvName: TextView, tvOs: TextView, ivImage: ImageView, phoneDetail: PhonesResponseItem?) {
            if (phoneDetail != null) {
                tvName.text = phoneDetail.name
                tvOs.text = phoneDetail.os
                Glide.with(context)
                    .load(phoneDetail.image)
                    .placeholder(R.drawable.ic_image)
                    .error(R.drawable.ic_image)
                    .into(ivImage)
            } else {
                tvName.text = ""
                tvOs.text = ""
                ivImage.setImageDrawable(null)
            }
        }

        private fun getPhoneDetailById(phoneId: Int): PhonesResponseItem? {
            return phoneDetails.find { it.id == phoneId }
        }
    }

    fun updateHistory(newHistoryList: List<History>) {
        val diffCallback = HistoryDiffCallback(historyList, newHistoryList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        historyList = newHistoryList
        diffResult.dispatchUpdatesTo(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updatePhonesDetails(phoneDetails: List<PhonesResponseItem>) {
        this.phoneDetails = phoneDetails
        notifyDataSetChanged()
    }

    class HistoryDiffCallback(private val oldList: List<History>, private val newList: List<History>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
