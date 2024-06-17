package com.capstoneproject.auxilium.view.newarrivals

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.databinding.ActivityDetailNewArrivalsBinding
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.helper.FormatterUtil
import com.capstoneproject.auxilium.response.PhonesResponseItem
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class DetailNewArrivalsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNewArrivalsBinding
    private lateinit var viewModel: DetailNewArrivalsViewModel
    private lateinit var userPreference: UserPreference

    private lateinit var ratingButtons: List<androidx.constraintlayout.utils.widget.ImageFilterButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewArrivalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreference = UserPreference.getInstance(this)

        ratingButtons = listOf(
            binding.btnStar1, binding.btnStar2, binding.btnStar3, binding.btnStar4, binding.btnStar5
        )

        lifecycleScope.launch {
            val token = userPreference.getToken().firstOrNull()
            val apiService = ApiConfig.getApiService(token)
            val repository = DetailNewArrivalsRepository(apiService)
            viewModel = ViewModelProvider(this@DetailNewArrivalsActivity, DetailNewArrivalsViewModelFactory(repository))[DetailNewArrivalsViewModel::class.java]

            val phone = intent.getParcelableExtra<PhonesResponseItem>("PHONE_DATA")
            phone?.let {
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
                    tvRatePhones.text = "What would you rate ${it.name}?"
                    Glide.with(this@DetailNewArrivalsActivity).load(it.image).into(ivPhoneImages)
                }
            }

            binding.btnAddWishlist.setOnClickListener {
                lifecycleScope.launch {
                    userPreference.getUserId().collect { userId ->
                        if (userId != null && phone != null) {
                            viewModel.addWishlist(userId, phone.id!!)
                        }
                    }
                }
            }

            viewModel.addWishlistResult.observe(this@DetailNewArrivalsActivity) { response ->
                Toast.makeText(this@DetailNewArrivalsActivity, response.msg, Toast.LENGTH_SHORT).show()
            }

            viewModel.error.observe(this@DetailNewArrivalsActivity) { errorMessage ->
                Toast.makeText(this@DetailNewArrivalsActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }

            ratingButtons.forEachIndexed { index, button ->
                button.setOnClickListener {
                    val rating = (index + 1).toString()[0]
                    lifecycleScope.launch {
                        userPreference.getUserId().collect { userId ->
                            if (userId != null && phone != null) {
                                viewModel.addUserRating(userId, phone.id!!, rating)
                            }
                        }
                    }
                    updateRating(index + 1)
                }
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
