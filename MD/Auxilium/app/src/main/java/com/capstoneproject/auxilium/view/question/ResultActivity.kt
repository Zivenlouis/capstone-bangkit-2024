package com.capstoneproject.auxilium.view.question

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.databinding.ActivityResultBinding
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.helper.FormatterUtil
import com.capstoneproject.auxilium.response.PhonesResponseItem
import com.capstoneproject.auxilium.view.question.detailresult.DetailResultActivity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var viewModel: ResultViewModel
    private lateinit var resultAdapter: ResultAdapter
    private lateinit var userPreference: UserPreference
    private var firstPhone: PhonesResponseItem? = null
    private var userClick: MutableList<Int> = MutableList(96) { 0 }

    private lateinit var ratingButtons: List<androidx.constraintlayout.utils.widget.ImageFilterButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreference = UserPreference(this)
        lifecycleScope.launch {
            val token = userPreference.getToken().firstOrNull()
            val apiService = ApiConfig.getApiService(token)
            val repository = ResultRepository(userPreference, apiService)
            val factory = ResultViewModelFactory(repository)
            viewModel = ViewModelProvider(this@ResultActivity, factory)[ResultViewModel::class.java]

            ratingButtons = listOf(
                binding.btnStar1, binding.btnStar2, binding.btnStar3, binding.btnStar4, binding.btnStar5
            )

            val recommendationIds = intent.getIntegerArrayListExtra("recommendations") ?: emptyList()

            resultAdapter = ResultAdapter { phone ->
                handlePhoneClick(phone)
            }

            binding.rvRecommended.apply {
                layoutManager = LinearLayoutManager(this@ResultActivity, LinearLayoutManager.HORIZONTAL, false)
                adapter = resultAdapter
            }

            viewModel.phoneList.observe(this@ResultActivity) { phones ->
                phones?.let {
                    if (phones.isNotEmpty()) {
                        firstPhone = phones[0]
                        displayPhoneDetails(firstPhone!!)

                        val remainingPhones = phones.drop(1)
                        resultAdapter.submitList(remainingPhones)
                    }
                }
            }

            viewModel.fetchPhones(recommendationIds)

            binding.btnAddWishlist.setOnClickListener {
                handleAddWishlist()
            }

            viewModel.addWishlistResult.observe(this@ResultActivity) { response ->
                Toast.makeText(this@ResultActivity, response.msg, Toast.LENGTH_SHORT).show()
            }

            viewModel.error.observe(this@ResultActivity) { errorMessage ->
                Toast.makeText(this@ResultActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }

            viewModel.addRatingsResult.observe(this@ResultActivity) { response ->
                Toast.makeText(this@ResultActivity, response.msg, Toast.LENGTH_SHORT).show()
            }

            ratingButtons.forEachIndexed { index, button ->
                button.setOnClickListener {
                    handleRating(index + 1)
                }
            }
        }
    }

    private fun handlePhoneClick(phone: PhonesResponseItem) {
        lifecycleScope.launch {
            val userId = userPreference.getUserId().firstOrNull()
            if (userId != null) {
                userClick[phone.id!!] = 1
                viewModel.addUserClick(userId, phone.id)
                val intent = Intent(this@ResultActivity, DetailResultActivity::class.java).apply {
                    putExtra("phone", phone)
                }
                startActivity(intent)
            }
        }
    }

    private fun handleAddWishlist() {
        lifecycleScope.launch {
            val userId = userPreference.getUserId().firstOrNull()
            if (userId != null && firstPhone != null) {
                viewModel.addWishlist(userId, firstPhone!!.id!!)
            }
        }
    }

    private fun handleRating(rating: Int) {
        lifecycleScope.launch {
            val userId = userPreference.getUserId().firstOrNull()
            if (userId != null && firstPhone != null) {
                viewModel.addUserRating(userId, firstPhone!!.id!!, rating.toString()[0])
            }
            updateRating(rating)
        }
    }

    private fun displayPhoneDetails(phone: PhonesResponseItem) {
        phone.let {
            binding.apply {
                tvPhoneNames.text = it.name
                tvPhoneOs.text = it.os
                tvBrandLabel.text = it.brand
                tvReleasedDateLabel.text = FormatterUtil.formatDate(it.releaseDate)
                tvResolutionLabel.text = FormatterUtil.formatResolution(it.resolution)
                tvWeightLabel.text = FormatterUtil.formatWeight(it.weight)
                tvChipsetLabel.text = it.chipset
                tvMemoryLabel.text = FormatterUtil.formatMemory(it.memory)
                tvRamLabel.text = FormatterUtil.formatMemory(it.ram)
                tvMainCamera1Label.text = FormatterUtil.formatCamera(it.mainCamera1)
                tvMainCamera2Label.text = FormatterUtil.formatCamera(it.mainCamera2)
                tvMainCamera3Label.text = FormatterUtil.formatCamera(it.mainCamera3)
                tvSelfieCameraLabel.text = FormatterUtil.formatCamera(it.selfieCamera)
                tvEarphoneJackLabel.text = FormatterUtil.formatYesNo(it.earphoneJack)
                tvBatteryLabel.text = FormatterUtil.formatBattery(it.battery)
                tvChargingLabel.text = FormatterUtil.formatCharging(it.charging)
                tvColorsLabel.text = it.colors
                tvNfcLabel.text = FormatterUtil.formatYesNo(it.nfc)
                tvPriceLabel.text = FormatterUtil.formatPrice(it.price)
                Glide.with(this@ResultActivity).load(it.image).into(ivPhoneImages)
            }
        }
    }

    private fun updateRating(selectedStars: Int) {
        ratingButtons.forEachIndexed { index, button ->
            if (index < selectedStars) {
                button.setImageResource(R.drawable.ic_star_full)
            } else {
                button.setImageResource(R.drawable.ic_star_border)
            }
        }
    }
}
