package com.capstoneproject.auxilium.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.FragmentHomeBinding
import com.capstoneproject.auxilium.history.HistoryActivity
import com.capstoneproject.auxilium.view.question.QuestActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnSuperchargeSearch.setOnClickListener {
            val intent = Intent(requireContext(), QuestActivity::class.java)
            startActivity(intent)
        }

        binding.btnHistory.setOnClickListener {
            val intent = Intent(requireContext(), HistoryActivity::class.java)
            startActivity(intent)
        }

        val userPhoneList = listOf(
            PhoneItem(R.drawable.ic_image, "Phone 1", "Some description"),
            PhoneItem(R.drawable.ic_image, "Phone 1", "Some description")
        )
        val recommendedList = listOf(
            PhoneItem(R.drawable.ic_image, "Phone 1", "Some description"),
            PhoneItem(R.drawable.ic_image, "Phone 1", "Some description")
        )

        val newArrivalsAdapter = NewArrivalsAdapter(userPhoneList)
        val mainRecAdapter = MainRecAdapter(recommendedList)

        binding.rvNewArrivals.adapter = newArrivalsAdapter
        binding.rvMainRecom.adapter = mainRecAdapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgressBar() {
        val progressBar = requireActivity().findViewById<ProgressBar>(R.id.progress_bar_loading)
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        val progressBar = requireActivity().findViewById<ProgressBar>(R.id.progress_bar_loading)
        progressBar.visibility = View.GONE
    }

}
