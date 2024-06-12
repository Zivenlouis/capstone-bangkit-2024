package com.capstoneproject.auxilium.ui.wishlist

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.databinding.ActivityDetailWishlistBinding
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.response.PhonesResponseItem
import java.text.NumberFormat
import java.util.Locale

class DetailWishlistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailWishlistBinding
    private lateinit var viewModel: WishlistViewModel
    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailWishlistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userPreference = UserPreference.getInstance(this)

        viewModel = ViewModelProvider(this, WishlistViewModelFactory(userPreference))[WishlistViewModel::class.java]

        val phone = intent.getParcelableExtra<PhonesResponseItem>("PHONE_DATA")
        val wishlistId = intent.getIntExtra("WISHLIST_ID", -1)
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
                Glide.with(this@DetailWishlistActivity).load(it.image).into(ivPhoneImages)
            }
        }

        binding.btnDeleteWishlist.setOnClickListener {
            if (wishlistId != -1) {
                viewModel.deleteWishlistByIdAndNotify(wishlistId) {
                    setResult(Activity.RESULT_OK)
                    finish()
                }
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
