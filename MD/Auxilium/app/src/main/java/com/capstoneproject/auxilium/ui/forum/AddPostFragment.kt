package com.capstoneproject.auxilium.ui.forum

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
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
            startCamera()
        }

        binding.btnGallery.setOnClickListener {
            startGallery()
        }

        binding.edAddDescription.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    binding.tiAddPost.error = null
                } else {
                    binding.tiAddPost.error = "Please enter a caption"
                }
            }
        }

        binding.btnPost.setOnClickListener {
            val captionText = binding.edAddDescription.text.toString().trim()

            if (captionText.isNotEmpty()) {
                val captionRequestBody: okhttp3.RequestBody =
                    captionText.toRequestBody("text/plain".toMediaTypeOrNull())

                if (currentPhotoPath != null) {
                    uploadPost(captionRequestBody, File(currentPhotoPath!!))
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Please select a photo",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(requireContext(), "Caption cannot be empty", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun uploadPost(captionRequestBody: okhttp3.RequestBody, file: File) {
        viewLifecycleOwner.lifecycleScope.launch {
            val isUploadSuccessful = viewModel.uploadPost(captionRequestBody, file)
            if (isUploadSuccessful) {
                Toast.makeText(requireContext(), "Post added successfully ", Toast.LENGTH_SHORT)
                    .show()
                parentFragmentManager.setFragmentResult("postAdded", Bundle())
                dismiss()
            } else {
                Toast.makeText(requireContext(), "Failed to upload post", Toast.LENGTH_SHORT).show()
            }
        }
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
