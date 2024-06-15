package com.capstoneproject.auxilium.view.question.quest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.FragmentQuest7Binding
import com.capstoneproject.auxilium.datastore.UserPreference
import com.capstoneproject.auxilium.view.question.InferenceLoadingActivity

class Quest7Fragment : Fragment() {

    private lateinit var binding: FragmentQuest7Binding
    private val repository by lazy { QuestionnaireRepository(UserPreference.getInstance(requireContext())) }
    private val questionnaireViewModel: QuestionnaireViewModel by viewModels {
        QuestionnaireViewModelFactory(repository)
    }
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
            if (areAllQuestionsAnswered()) {
                sendUserSurvey()
            } else {
                // Handle case where not all questions are answered
                // Optionally show a message to the user
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.Fragment7toFragment6)
        }

        observeViewModel()
    }

    private fun areAllQuestionsAnswered(): Boolean {
        return sharedViewModel.getQuestion1Response() != null &&
                sharedViewModel.getQuestion2Response() != null &&
                sharedViewModel.getQuestion3Response() != null &&
                sharedViewModel.getQuestion4Response() != null &&
                sharedViewModel.getQuestion5Response() != null &&
                sharedViewModel.getQuestion6Response() != null &&
                sharedViewModel.getQuestion7Response() != null
    }

    private fun sendUserSurvey() {
        val userSurveyRequest = sharedViewModel.getAllResponses()
        questionnaireViewModel.getRecommendations(userSurveyRequest)
    }

    private fun observeViewModel() {
        questionnaireViewModel.recommendations.observe(viewLifecycleOwner) { recommendations ->
            if (recommendations.isNotEmpty()) {
                val intent = Intent(requireContext(), InferenceLoadingActivity::class.java)
                intent.putIntegerArrayListExtra("recommendations", ArrayList(recommendations))
                startActivity(intent)

                requireActivity().finish()
            }
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
            else -> View.NO_ID.toString()
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
            "Lainnya / Tidak Ada" -> "lainnya/tidak ada"
            else -> ""
        }
    }
}
