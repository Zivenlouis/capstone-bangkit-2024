package com.capstoneproject.auxilium.ui.forum

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
import androidx.lifecycle.lifecycleScope
import com.capstoneproject.auxilium.databinding.FragmentAddPostBinding
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.helper.getImageUri
import com.capstoneproject.auxilium.helper.reduceFileImage
import com.capstoneproject.auxilium.helper.uriToFile
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class AddPostFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddPostBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddPostViewModel by viewModels {
        AddPostViewModelFactory(
            UserPreference.getInstance(requireContext())
        )
    }

    private var currentPhotoPath: String? = null
    private var currentImageUri: Uri? = null

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(requireContext(), "Permission request granted", Toast.LENGTH_SHORT)
                    .show()
                startGallery()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Permission denied to read your External storage",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    private val launcherGallery =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                currentImageUri = uri
                showImageFromUri(uri)
            } else {
                Toast.makeText(requireContext(), "No media selected", Toast.LENGTH_SHORT).show()
            }
        }

    private val launcherIntentCamera =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                currentImageUri?.let { uri ->
                    showImageFromUri(uri)
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCamera.setOnClickListener {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                requestPermissions()
            }
        }

        binding.btnGallery.setOnClickListener {
            checkPermissionAndStartGallery()
        }

        binding.btnPost.setOnClickListener {
            val captionText = binding.edAddDescription.text.toString().trim()
            val captionRequestBody = captionText.toRequestBody("text/plain".toMediaTypeOrNull())

            if (currentPhotoPath != null) {
                viewLifecycleOwner.lifecycleScope.launch {
                    val file = File(currentPhotoPath!!)
                    val isUploadSuccessful = viewModel.uploadPost(captionRequestBody, file)
                    if (isUploadSuccessful) {
                        parentFragmentManager.setFragmentResult("postAdded", Bundle())
                        dismiss()
                    } else {
                        Toast.makeText(requireContext(), "Failed to upload post", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please select a photo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED

    private fun checkPermissionAndStartGallery() {
        if (!allPermissionsGranted()) {
            requestPermissions()
        } else {
            startGallery()
        }
    }

    private fun requestPermissions() {
        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun startGallery() {
        launcherGallery.launch("image/*")
    }

    private fun startCamera() {
        currentImageUri = getImageUri(requireContext())
        launcherIntentCamera.launch(currentImageUri)
    }

    private fun showImageFromUri(uri: Uri) {
        val imageFile = uriToFile(uri, requireContext()).reduceFileImage()
        binding.ivShowImage.setImageBitmap(BitmapFactory.decodeFile(imageFile.path))
        currentPhotoPath = imageFile.absolutePath
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
