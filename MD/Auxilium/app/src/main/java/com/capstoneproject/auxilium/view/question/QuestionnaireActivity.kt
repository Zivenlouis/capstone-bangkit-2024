package com.capstoneproject.auxilium.view.question

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstoneproject.auxilium.databinding.ActivityQuestionnaireBinding

class QuestionnaireActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionnaireBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionnaireBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}

