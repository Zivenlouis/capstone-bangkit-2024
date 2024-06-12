package com.capstoneproject.auxilium.view.newarrivals

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capstoneproject.auxilium.databinding.ActivityNewArrivalsBinding

class NewArrivalsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewArrivalsBinding
    private lateinit var viewModel: NewArrivalsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewArrivalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, NewArrivalsViewModelFactory(this))[NewArrivalsViewModel::class.java]

        val newArrivalsAdapter = NewArrivalsAdapter { phone ->
            val intent = Intent(this, DetailNewArrivalsActivity::class.java).apply {
                putExtra("PHONE_DATA", phone)
            }
            startActivity(intent)
        }

        binding.rvNewArrivalsDetails.apply {
            adapter = newArrivalsAdapter
        }

        viewModel.phoneList.observe(this) { phones ->
            if (phones != null) {
                newArrivalsAdapter.submitList(phones)
            }
        }

        viewModel.fetchPhones()
    }
}
