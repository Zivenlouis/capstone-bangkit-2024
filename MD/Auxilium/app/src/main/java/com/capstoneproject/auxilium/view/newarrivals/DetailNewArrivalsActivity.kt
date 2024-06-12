package com.capstoneproject.auxilium.view.newarrivals

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.databinding.ActivityDetailNewArrivalsBinding
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.PhonesResponseItem
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class DetailNewArrivalsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNewArrivalsBinding
    private lateinit var viewModel: DetailNewArrivalsViewModel
    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewArrivalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreference = UserPreference.getInstance(this)

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
                    tvReleasedDateLabel.text = it.releaseDate
                    tvResolutionLabel.text = it.resolution
                    tvWeightLabel.text = it.weight
                    tvChipsetLabel.text = it.chipset
                    tvMemoryLabel.text = it.memory
                    tvRamLabel.text = it.ram
                    tvMainCamera1Label.text = it.mainCamera1
                    tvMainCamera2Label.text = it.mainCamera2
                    tvMainCamera3Label.text = it.mainCamera3
                    tvSelfieCameraLabel.text = it.selfieCamera
                    tvEarphoneJackLabel.text = it.earphoneJack
                    tvBatteryLabel.text = it.battery
                    tvChargingLabel.text = it.charging
                    tvColorsLabel.text = it.colors
                    tvNfcLabel.text = it.nfc
                    tvPriceLabel.text = formatPrice(it.price)
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
        }
    }

    private fun formatPrice(price: String?, countryCode: String = "ID"): String {
        return try {
            val priceLong = price?.toLongOrNull()
            if (priceLong != null) {
                val locale = if (countryCode.equals("ID", ignoreCase = true)) Locale("id", "ID") else Locale.US
                val format = NumberFormat.getCurrencyInstance(locale)
                if (locale == Locale("id", "ID")) {
                    format.format(priceLong).replace("Rp", "Rp.")
                } else {
                    format.format(priceLong)
                }
            } else {
                price.orEmpty()
            }
        } catch (e: Exception) {
            price.orEmpty()
        }
    }
}
