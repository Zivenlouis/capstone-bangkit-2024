package com.capstoneproject.auxilium.view.question.quest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.FragmentQuest5Binding
import com.capstoneproject.auxilium.view.question.SharedViewModel

class Quest5Fragment : Fragment() {

    private lateinit var binding: FragmentQuest5Binding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentQuest5Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val question5Response = sharedViewModel.getQuestion5Response()
        setSelectedRadioButton(question5Response)

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)
            radioButton?.let {
                val selectedOption = mapRadioButtonTextToInt(it.text.toString())
                sharedViewModel.setQuestion5Response(selectedOption)
            }
        }

        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.Fragment5toFragment6)
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.Fragment5toFragment4)
        }
    }

    private fun setSelectedRadioButton(response: Int) {
        when (response) {
            1 -> binding.radio1.isChecked = true
            2 -> binding.radio2.isChecked = true
            3 -> binding.radio3.isChecked = true
            4 -> binding.radio4.isChecked = true
            5 -> binding.radio5.isChecked = true
        }
    }

    private fun mapRadioButtonTextToInt(text: String): Int {
        return when (text) {
            "1" -> 1
            "2" -> 2
            "3" -> 3
            "4" -> 4
            "5" -> 5
            else -> {
                0
            }
        }
    }
}
