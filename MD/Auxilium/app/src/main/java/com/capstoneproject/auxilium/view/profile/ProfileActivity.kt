package com.capstoneproject.auxilium.view.profile

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.databinding.ActivityProfileBinding
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.login.LoginActivity
import com.capstoneproject.auxilium.view.MainActivity
import com.capstoneproject.auxilium.view.additional.AboutUsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val userPreference: UserPreference by lazy { UserPreference.getInstance(this) }
    private lateinit var repository: ProfileRepository
    private val viewModel: ProfileViewModel by viewModels {
        ProfileViewModelFactory(userPreference, repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val token = userPreference.getToken().firstOrNull()
            repository = ProfileRepository(ApiConfig.getApiService(token))
            viewModel.fetchUserProfile()  // Fetch the user profile
            observeViewModel()
        }

        binding.btnBackProfile.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnLanguage.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }

        binding.btnResetPassword.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                userPreference.clearToken()
                Toast.makeText(this@ProfileActivity, "Logout successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }

        binding.btnAboutUs.setOnClickListener {
            val intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userProfile.collectLatest { user ->
                    user?.let {
                        binding.apply {
                            textName.text = it.name
                            textEmail.text = it.email
                            textDateJoined.text = formatDate(it.createdAt)
                            Glide.with(this@ProfileActivity).load(it.profileImage).into(imageAvatar)
                        }
                    }
                }
            }
        }
    }

    private fun formatDate(dateString: String?): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        val date = dateString?.let { inputFormat.parse(it) }
        return date?.let {
            "Joined ${outputFormat.format(it)}"
        } ?: "Joined Unknown Date"
    }

}
