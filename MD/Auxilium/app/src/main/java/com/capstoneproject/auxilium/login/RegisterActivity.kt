package com.capstoneproject.auxilium.login

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.capstoneproject.auxilium.databinding.ActivityRegisterBinding
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.helper.getImageUri
import com.capstoneproject.auxilium.helper.reduceFileImage
import com.capstoneproject.auxilium.helper.uriToFile
import kotlinx.coroutines.launch
import java.io.File

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels {
        RegisterViewModelFactory(RegisterRepository(UserPreference.getInstance(applicationContext)))
    }

    private lateinit var launcherGallery: ActivityResultLauncher<String>
    private lateinit var launcherIntentCamera: ActivityResultLauncher<Uri>

    private var currentPhotoPath: String? = null
    private var selectedImageFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launcherGallery = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                val imageFile = uriToFile(it, this).reduceFileImage()
                binding.ivShowImage.setImageURI(Uri.fromFile(imageFile))
                selectedImageFile = imageFile
            } ?: Toast.makeText(this, "No media selected", Toast.LENGTH_SHORT).show()
        }

        launcherIntentCamera = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                currentPhotoPath?.let { path ->
                    val imageFile = uriToFile(Uri.parse(path), this).reduceFileImage()
                    binding.ivShowImage.setImageBitmap(BitmapFactory.decodeFile(imageFile.path))
                    selectedImageFile = imageFile
                }
            }
        }

        binding.btnGallery.setOnClickListener {
            startGallery()
        }

        binding.btnCamera.setOnClickListener {
            startCamera()
        }

        binding.btnRegister.setOnClickListener {
            val username = binding.edUsernameRegister.text.toString()
            val email = binding.edEmailRegister.text.toString()
            val password = binding.edPasswordRegister.text.toString()
            val confirmPassword = binding.edConfirmPasswordRegister.text.toString()

            if (!isValidEmail(email)) {
                binding.textInputEmailLogin.error = "Please enter a valid email address."
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                binding.textInputPasswordLogin.error = "Password and confirm password do not match."
                binding.textInputConfirmPassword.error =
                    "Password and confirm password do not match."
                return@setOnClickListener
            }

            selectedImageFile?.let { file ->
                lifecycleScope.launch {
                    try {
                        val response =
                            viewModel.register(username, email, password, confirmPassword, file)

                        if (response.id != null) {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Registration successful",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            response.message?.let {
                                Toast.makeText(this@RegisterActivity, it, Toast.LENGTH_SHORT).show()
                            } ?: run {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Registration failed: Unknown error",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } catch (e: RegistrationException) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Registration failed: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (e: Exception) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Registration failed: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                        e.printStackTrace()
                    }
                }
            } ?: run {
                Toast.makeText(
                    this@RegisterActivity,
                    "Please select a file first",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.tvSignIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        setupTextWatchers()
    }

    private fun setupTextWatchers() {
        binding.edEmailRegister.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!isValidEmail(s.toString())) {
                    binding.textInputEmailLogin.error = "Please enter a valid email address."
                } else {
                    binding.textInputEmailLogin.error = null
                }
            }
        })

        binding.edPasswordRegister.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != binding.edConfirmPasswordRegister.text.toString()) {
                    binding.textInputPasswordLogin.error =
                        "Password and confirm password do not match."
                    binding.textInputConfirmPassword.error =
                        "Password and confirm password do not match."
                } else {
                    binding.textInputPasswordLogin.error = null
                    binding.textInputConfirmPassword.error = null
                }
            }
        })

        binding.edConfirmPasswordRegister.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != binding.edPasswordRegister.text.toString()) {
                    binding.textInputPasswordLogin.error =
                        "Password and confirm password do not match."
                    binding.textInputConfirmPassword.error =
                        "Password and confirm password do not match."
                } else {
                    binding.textInputPasswordLogin.error = null
                    binding.textInputConfirmPassword.error = null
                }
            }
        })
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun startGallery() {
        launcherGallery.launch("image/*")
    }

    private fun startCamera() {
        val photoURI: Uri = getImageUri(this)
        currentPhotoPath = photoURI.toString()
        launcherIntentCamera.launch(photoURI)
    }
}
