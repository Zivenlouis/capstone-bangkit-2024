package com.capstoneproject.auxilium.view.question

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.ActivityQuestionnaireBinding

class QuestionnaireActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionnaireBinding

    private val questions = listOf(
        Question("Apakah mempertimbangkan brand hp?", listOf("Ya", "Tidak")),
        Question(
            "Brand favorit?", listOf(
                "Apple", "Samsung", "Asus", "Oppo", "Vivo",
                "Realme", "Infinix", "Xiaomi", "Huawei", "Lainnya/tidak ada", "Poco"
            )
        ),
        Question("Seberapa penting performa?", listOf("1", "2", "3", "4", "5")),
        Question("Seberapa penting kamera?", listOf("1", "2", "3", "4", "5")),
        Question("Seberapa penting baterai?", listOf("1", "2", "3", "4", "5")),
        Question("Seberapa penting software?", listOf("1", "2", "3", "4", "5")),
        Question("Seberapa penting RAM?", listOf("1", "2", "3", "4", "5")),
        Question("Seberapa penting ruang penyimpanan?", listOf("1", "2", "3", "4", "5"))
    )

    private var currentQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionnaireBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateQuestionUI(currentQuestionIndex)

        binding.nextButton.setOnClickListener {
            currentQuestionIndex++
            updateQuestionUI(currentQuestionIndex)
        }

        binding.backButton.setOnClickListener {
            currentQuestionIndex--
            updateQuestionUI(currentQuestionIndex)
        }
    }

    private fun updateQuestionUI(index: Int) {
        if (index < 0) {
            currentQuestionIndex = 0
            return
        }

        if (index >= questions.size) {
            currentQuestionIndex = questions.size - 1
            return
        }

        val question = questions[index]

        binding.questionNumbs.text = "Question ${index + 1}"
        binding.questionText.text = question.questionText

        binding.radioGroup.removeAllViews()
        question.options.forEach { option ->
            val radioButton = RadioButton(this)
            radioButton.text = option

            val customFont = Typeface.create("clash-grotesk-reguler", Typeface.NORMAL)
            radioButton.typeface = customFont
            val colorOption = ContextCompat.getColor(this, R.color.light_peach)
            radioButton.setTextColor(colorOption)
            radioButton.textSize = 18f
            val layoutParams = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(10, 16, 0, 0)
            radioButton.layoutParams = layoutParams

            binding.radioGroup.addView(radioButton)
        }

        // Toggle back button visibility
        binding.backButton.visibility = if (currentQuestionIndex == 0) View.GONE else View.VISIBLE

        // Toggle next button visibility
        binding.nextButton.text =
            if (currentQuestionIndex == questions.size - 1) "Finish" else "Next"
        binding.nextButton.setOnClickListener {
            if (currentQuestionIndex == questions.size - 1) {
                // Navigate to InferenceLoadingActivity
                val intent = Intent(this, InferenceLoadingActivity::class.java)
                startActivity(intent)
            } else {
                currentQuestionIndex++
                updateQuestionUI(currentQuestionIndex)
            }
        }
    }
}


