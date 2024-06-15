package com.capstoneproject.auxilium.view.question.detailresult

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.databinding.ActivityDetailResultBinding
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.helper.FormatterUtil
import com.capstoneproject.auxilium.response.PhonesResponseItem
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class DetailResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailResultBinding
    private lateinit var viewModel: DetailResultViewModel
    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreference = UserPreference.getInstance(this)

        lifecycleScope.launch {
            val token = userPreference.getToken().firstOrNull()
            val apiService = ApiConfig.getApiService(token)
            val repository = DetailResultRepository(apiService)
            ViewModelProvider(
                this@DetailResultActivity,
                DetailResultViewModelFactory(repository)
            )[DetailResultViewModel::class.java].also { viewModel = it }

            val phone = intent.getParcelableExtra<PhonesResponseItem>("phone")
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
                    Glide.with(this@DetailResultActivity).load(it.image).into(ivPhoneImages)
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

            viewModel.addWishlistResult.observe(this@DetailResultActivity) { response ->
                Toast.makeText(this@DetailResultActivity, response.msg, Toast.LENGTH_SHORT).show()
            }

            viewModel.error.observe(this@DetailResultActivity) { errorMessage ->
                Toast.makeText(this@DetailResultActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

}
