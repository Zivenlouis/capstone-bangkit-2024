package com.capstoneproject.auxilium.view.question.quest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.FragmentQuest7Binding
import com.capstoneproject.auxilium.view.question.InferenceLoadingActivity
import com.capstoneproject.auxilium.view.question.SharedViewModel

class Quest7Fragment : Fragment() {

    private lateinit var binding: FragmentQuest7Binding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuest7Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val question7Response = sharedViewModel.getQuestion7Response()
        setSelectedRadioButton(question7Response)

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)
            radioButton?.let {
                val selectedOption = getRadioButtonId(it.text.toString())
                sharedViewModel.setQuestion7Response(selectedOption)
            }
        }

        binding.btnFinish.setOnClickListener {
            val selectedRadioButtonId = binding.radioGroup.checkedRadioButtonId
            if (selectedRadioButtonId == View.NO_ID) {
                sharedViewModel.setQuestion7Response("lainnya/tidak ada")
            }

            val responses = arrayListOf(
                sharedViewModel.getQuestion1Response(),
                sharedViewModel.getQuestion2Response(),
                sharedViewModel.getQuestion3Response(),
                sharedViewModel.getQuestion4Response(),
                sharedViewModel.getQuestion5Response(),
                sharedViewModel.getQuestion6Response(),
                sharedViewModel.getQuestion7Response()
            )

            val intent = Intent(requireContext(), InferenceLoadingActivity::class.java)
            intent.putExtra("user_survey", responses)
            startActivity(intent)
            requireActivity().finish()
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.Fragment7toFragment6)
        }
    }

    private fun setSelectedRadioButton(response: String) {
        when (response) {
            "Apple" -> binding.radio1.isChecked = true
            "Samsung" -> binding.radio2.isChecked = true
            "Asus" -> binding.radio3.isChecked = true
            "Oppo" -> binding.radio4.isChecked = true
            "Vivo" -> binding.radio5.isChecked = true
            "Realme" -> binding.radio6.isChecked = true
            "Xiaomi" -> binding.radio7.isChecked = true
            "Infinix" -> binding.radio8.isChecked = true
            "Huawei" -> binding.radio9.isChecked = true
            "Poco" -> binding.radio10.isChecked = true
            "lainnya/tidak ada" -> binding.radio11.isChecked = true
            else -> binding.radio11.isChecked = true
        }
    }

    private fun getRadioButtonId(text: String): String {
        return when (text) {
            "Apple" -> "Apple"
            "Samsung" -> "Samsung"
            "Asus" -> "Asus"
            "Oppo" -> "Oppo"
            "Vivo" -> "Vivo"
            "Realme" -> "Realme"
            "Xiaomi" -> "Xiaomi"
            "Infinix" -> "Infinix"
            "Huawei" -> "Huawei"
            "Poco" -> "Poco"
            "None of the Options" -> "lainnya/tidak ada"
            else -> "lainnya/tidak ada"
        }
    }
}
