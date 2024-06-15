package com.capstoneproject.auxilium.view.profile

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
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

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(requireContext(), "Permission granted", Toast.LENGTH_SHORT).show()
                startGallery()
            } else {
                Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    private val launcherGallery = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val imageFile = uriToFile(it, requireContext()).reduceFileImage()
            binding.ivShowImage.setImageURI(Uri.fromFile(imageFile))
            selectedImageFile = imageFile
        } ?: Toast.makeText(requireContext(), "No media selected", Toast.LENGTH_SHORT).show()
    }

    private val launcherIntentCamera = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
        if (isSuccess) {
            currentPhotoPath?.let { path ->
                val imageFile = uriToFile(Uri.parse(path), requireContext()).reduceFileImage()
                binding.ivShowImage.setImageBitmap(BitmapFactory.decodeFile(imageFile.path))
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
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                requestPermissions()
            }
        }

        binding.btnGallery.setOnClickListener {
            if (allPermissionsGranted()) {
                startGallery()
            } else {
                requestPermissions()
            }
        }

        binding.btnSaveProfile.setOnClickListener {
            saveProfile()
        }

        observeViewModel()
    }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

    private fun requestPermissions() {
        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
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
        if (name.isEmpty()) {
            Toast.makeText(requireContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        val nameRequestBody = name.toRequestBody("text/plain".toMediaTypeOrNull())
        var profileImagePart: MultipartBody.Part? = null

        selectedImageFile?.let {
            val imageRequestBody = it.asRequestBody("image/jpeg".toMediaTypeOrNull())
            profileImagePart = MultipartBody.Part.createFormData("file", it.name, imageRequestBody)
        }

        viewModel.editProfile(nameRequestBody, email, profileImagePart)
    }

    private fun observeViewModel() {
        viewModel.editProfileResponse.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT)
                .show()
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
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
