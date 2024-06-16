package com.capstoneproject.auxilium.view.question

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstoneproject.auxilium.api.UserSurveyRequest
import com.capstoneproject.auxilium.databinding.ActivityInferenceLoadingBinding
import com.capstoneproject.auxilium.datastore.UserPreference

@Suppress("DEPRECATION")
class InferenceLoadingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInferenceLoadingBinding
    private lateinit var tvCrunchingLoading: TextView
    private lateinit var tvQuotes: TextView
    private val stringArray = arrayListOf(
        "Say no to overheating! Keep your phone cool and chill to avoid meltdowns.",
        "Power up! Battery life is key—choose a phone that can keep up with your busy lifestyle without constantly needing a recharge.",
        "Snap to it! If photography is your thing, prioritize a phone with a stellar camera and features like optical image stabilization and night mode.",
        "Do your research! Check out reviews and comparisons to find the perfect phone that suits your needs and budget.",
        "Set your budget and stick to it! Don't break the bank for features you don't need. There's a phone out there for every budget.",
        "Future-proof your choice! Look for phones with decent processing power and ample storage to keep up with future software updates and apps.",
        "Durability matters! Look for phones with water and dust resistance, and consider investing in a sturdy case for added protection."
    )
    private val handler = Handler()
    private var dotCount = 0
    private val repository by lazy { QuestionnaireRepository(UserPreference.getInstance(this)) }
    private val questionnaireViewModel: QuestionnaireViewModel by viewModels {
        QuestionnaireViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInferenceLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tvCrunchingLoading = binding.tvCrunchingLoading
        tvQuotes = binding.tvQuotes

        simulateLoadingAndFetchRecommendations()
    }

    private fun simulateLoadingAndFetchRecommendations() {
        handler.postDelayed({
            val loadingTime = 1000

            handler.postDelayed({
                sendUserSurvey()
            }, loadingTime.toLong())

            startLoadingAnimation()
        }, 0)
    }

    private fun sendUserSurvey() {
        val userSurveyList = intent.getSerializableExtra("user_survey") as ArrayList<*>
        Log.d("InferenceLoadingActivity", "User Survey Request: $userSurveyList")

        val userSurveyRequest = UserSurveyRequest(userSurveyList)
        questionnaireViewModel.getRecommendations(userSurveyRequest)
        observeViewModel()
    }

    private fun observeViewModel() {
        questionnaireViewModel.recommendations.observe(this) { recommendations ->
            if (recommendations.isNotEmpty()) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putIntegerArrayListExtra(
                    "recommendations",
                    recommendations as ArrayList<Int>
                )
                startActivity(intent)
                finish()
            }
        }
    }

    private fun startLoadingAnimation() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                val loadingText = StringBuilder("Crunching data just for you")
                for (i in 0 until dotCount) {
                    loadingText.append(".")
                }
                tvCrunchingLoading.text = loadingText.toString()
                dotCount = (dotCount + 1) % 4

                val randInt = (0 until stringArray.size).random()
                val randomTip = stringArray[randInt]

                tvQuotes.text = randomTip
                tvQuotes.alpha = 0f
                tvQuotes.animate().alpha(1f).setDuration(500).start()

                handler.postDelayed(this, 3000)
            }
        }, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
