package com.capstoneproject.auxilium.view.question

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.databinding.ActivityResultBinding
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.helper.FormatterUtil
import com.capstoneproject.auxilium.response.PhonesResponseItem
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var viewModel: ResultViewModel
    private lateinit var resultAdapter: ResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userPreference = UserPreference(this)
        lifecycleScope.launch {
            val token = userPreference.getToken().firstOrNull()
            val apiService = ApiConfig.getApiService(token)
            val repository = ResultRepository(userPreference, apiService)
            val factory = ResultViewModelFactory(repository)
            viewModel =
                ViewModelProvider(this@ResultActivity, factory)[ResultViewModel::class.java]

            val recommendationIds =
                intent.getIntegerArrayListExtra("recommendations") ?: emptyList()

            resultAdapter = ResultAdapter()
            binding.rvRecommended.apply {
                layoutManager =
                    LinearLayoutManager(this@ResultActivity, LinearLayoutManager.HORIZONTAL, false)
                adapter = resultAdapter
            }

            viewModel.phoneList.observe(this@ResultActivity) { phones ->
                phones?.let {
                    if (phones.isNotEmpty()) {
                        val firstPhone = phones[0]
                        displayPhoneDetails(firstPhone)

                        val remainingPhones = phones.drop(1)
                        resultAdapter.submitList(remainingPhones)
                    }
                }
            }

            viewModel.fetchPhones(recommendationIds)

            binding.btnAddWishlist.setOnClickListener {
                val firstPhone =
                    viewModel.phoneList.value?.get(0)
                lifecycleScope.launch {
                    userPreference.getUserId().collect { userId ->
                        firstPhone?.let {
                            viewModel.addWishlist(userId!!, it.id!!)
                        }
                    }
                }
            }

            viewModel.addWishlistResult.observe(this@ResultActivity) { response ->
                Toast.makeText(this@ResultActivity, response.msg, Toast.LENGTH_SHORT).show()
            }

            viewModel.error.observe(this@ResultActivity) { errorMessage ->
                Toast.makeText(this@ResultActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
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
}
