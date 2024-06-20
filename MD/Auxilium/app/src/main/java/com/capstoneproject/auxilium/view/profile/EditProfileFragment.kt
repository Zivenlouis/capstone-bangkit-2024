package com.capstoneproject.auxilium.view.profile

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.core.os.bundleOf
import com.capstoneproject.auxilium.databinding.FragmentEditProfileBinding
import com.capstoneproject.auxilium.helper.getImageUri
import com.capstoneproject.auxilium.helper.reduceFileImage
import com.capstoneproject.auxilium.helper.uriToFile
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class EditProfileFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EditProfileViewModel by viewModels {
        EditProfileViewModelFactory(requireContext())
    }

    private var currentPhotoPath: String? = null
    private var selectedImageFile: File? = null
    private lateinit var email: RequestBody

    private val launcherGallery =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                val imageFile = uriToFile(it, requireContext()).reduceFileImage()
                binding.ivShowImage.setImageURI(Uri.fromFile(imageFile))
                selectedImageFile = imageFile
            } ?: Toast.makeText(requireContext(), "No media selected", Toast.LENGTH_SHORT).show()
        }

    private val launcherIntentCamera =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                currentPhotoPath?.let { path ->
                    val imageFile = uriToFile(Uri.parse(path), requireContext()).reduceFileImage()
                    binding.ivShowImage.setImageURI(Uri.fromFile(imageFile))
                    selectedImageFile = imageFile
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString("username")
        val emailString = arguments?.getString("email") ?: ""
        email = emailString.toRequestBody("text/plain".toMediaTypeOrNull())
        val profileImagePath = arguments?.getString("profileImagePath")

        binding.edEditUsername.setText(username)

        profileImagePath?.let {
            val imageFile = File(it)
            binding.ivShowImage.setImageBitmap(BitmapFactory.decodeFile(imageFile.path))
            selectedImageFile = imageFile
        }

        binding.btnCamera.setOnClickListener {
            startCamera()
        }

        binding.btnGallery.setOnClickListener {
            startGallery()
        }

        binding.btnSaveProfile.setOnClickListener {
            saveProfile()
        }

        binding.edEditUsername.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    binding.tiEditUsername.error = null
                } else {
                    binding.tiEditUsername.error = "Please enter a username"
                }
            }
        }
        observeViewModel()
    }

    private fun startGallery() {
        launcherGallery.launch("image/*")
    }

    private fun startCamera() {
        val photoURI: Uri = getImageUri(requireContext())
        currentPhotoPath = photoURI.toString()
        launcherIntentCamera.launch(photoURI)
    }

    private fun saveProfile() {
        val name = binding.edEditUsername.text.toString().trim()
        if (name.isNotEmpty()) {
            val nameRequestBody = name.toRequestBody("text/plain".toMediaTypeOrNull())
            var profileImagePart: MultipartBody.Part? = null

            selectedImageFile?.let {
                val imageRequestBody = it.asRequestBody("image/jpeg".toMediaTypeOrNull())
                profileImagePart = MultipartBody.Part.createFormData("file", it.name, imageRequestBody)
            }

            viewModel.editProfile(nameRequestBody, email, profileImagePart)

            viewModel.editProfileResponse.observe(viewLifecycleOwner) { response ->
                if (response.isSuccessful) {
                    setFragmentResult("editProfileKey", bundleOf("isProfileUpdated" to true))
                    dismiss()
                } else {
                    Toast.makeText(requireContext(), "Failed to update profile", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(requireContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        viewModel.editProfileResponse.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isProfileUpdated.observe(viewLifecycleOwner) { isUpdated ->
            if (isUpdated) {
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
