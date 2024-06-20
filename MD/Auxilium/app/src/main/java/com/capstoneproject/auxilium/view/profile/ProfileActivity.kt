package com.capstoneproject.auxilium.view.profile

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.capstoneproject.auxilium.api.ApiConfig
import com.capstoneproject.auxilium.databinding.ActivityProfileBinding
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.login.LoginActivity
import com.capstoneproject.auxilium.view.MainActivity
import com.capstoneproject.auxilium.view.additional.AboutUsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val userPreference: UserPreference by lazy { UserPreference.getInstance(this) }
    private lateinit var repository: ProfileRepository
    private var viewModel: ProfileViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val token = userPreference.getToken().firstOrNull()
            repository = ProfileRepository(ApiConfig.getApiService(token))

            viewModel = ViewModelProvider(
                this@ProfileActivity,
                ProfileViewModelFactory(userPreference, repository)
            )[ProfileViewModel::class.java]

            viewModel?.fetchUserProfile()
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

        binding.btnEditProfile.setOnClickListener {
            navigateToEditProfile()
        }
    }

    private fun navigateToEditProfile() {
        val userProfile = viewModel?.userProfile?.value ?: return

        val bundle = Bundle().apply {
            putString("username", userProfile.name)
            putString("email", userProfile.email)
            putString("profileImagePath", userProfile.profileImage)
        }

        val editProfileFragment = EditProfileFragment().apply {
            arguments = bundle
        }

        editProfileFragment.show(supportFragmentManager, editProfileFragment.tag)

        supportFragmentManager.setFragmentResultListener("editProfileKey", this) { _, resultBundle ->
            val isProfileUpdated = resultBundle.getBoolean("isProfileUpdated")
            if (isProfileUpdated) {
                viewModel?.fetchUserProfile()
            }
        }
    }

    private fun observeViewModel() {
        viewModel?.userProfile?.observe(this) { user ->
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

    override fun onResume() {
        super.onResume()
        observeViewModel()
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
