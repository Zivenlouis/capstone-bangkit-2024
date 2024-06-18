package com.capstoneproject.auxilium.view.newarrivals

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.ActivityNewArrivalsBinding
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.view.profile.ProfileActivity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class NewArrivalsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewArrivalsBinding
    private lateinit var viewModel: NewArrivalsViewModel
    private lateinit var repository: NewArrivalsRepository
    private lateinit var userPreference: UserPreference
    private var userClick: MutableList<Int> = MutableList(96) { 0 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewArrivalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.custom_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        userPreference = UserPreference.getInstance(this)
        repository = NewArrivalsRepository(userPreference)
        viewModel = ViewModelProvider(
            this,
            NewArrivalsViewModelFactory(repository)
        )[NewArrivalsViewModel::class.java]

        val newArrivalsAdapter = NewArrivalsAdapter { phone ->
            lifecycleScope.launch {
                val userId = userPreference.getUserId().firstOrNull() ?: return@launch
                userClick[phone.id!!] = 1
                viewModel.addUserClick(userId, phone.id)
                val intent =
                    Intent(this@NewArrivalsActivity, DetailNewArrivalsActivity::class.java).apply {
                        putExtra("PHONE_DATA", phone)
                    }
                startActivity(intent)
            }
        }

        binding.rvNewArrivalsDetails.apply {
            adapter = newArrivalsAdapter
        }

        val firstNewArrivalsAdapter = FirstNewArrivalsAdapter { phone ->
            lifecycleScope.launch {
                val userId = userPreference.getUserId().firstOrNull() ?: return@launch
                userClick[phone.id!!] = 1
                viewModel.addUserClick(userId, phone.id)
                val intent =
                    Intent(this@NewArrivalsActivity, DetailNewArrivalsActivity::class.java).apply {
                        putExtra("PHONE_DATA", phone)
                    }
                startActivity(intent)
            }
        }

        binding.rvNewArrivalsFirst.apply {
            adapter = firstNewArrivalsAdapter
        }

        viewModel.phoneList.observe(this) { phones ->
            if (phones != null) {
                newArrivalsAdapter.submitList(phones)
                firstNewArrivalsAdapter.submitList(phones.take(10))
            }
        }
        viewModel.fetchPhones()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.upper_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
