package com.capstoneproject.auxilium.view.additional

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstoneproject.auxilium.R
import com.capstoneproject.auxilium.databinding.ActivityAboutUsBinding

class AboutUsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val teamsList = getTeams()

        binding.rvTeams.layoutManager = LinearLayoutManager(this)
        binding.rvTeams.adapter = AboutUsAdapter(teamsList)
    }

    private fun getTeams(): List<Team> {
        return listOf(
            Team(
                R.drawable.ic_image,
                "Filbert Wijaya",
                "Machine Learning Engineer",
                "Teamwork makes the dream work"
            ),
            Team(
                R.drawable.ic_image,
                "Jason Tjoa",
                "Machine Learning Engineer",
                "Design is intelligence made visible"
            ),
            Team(R.drawable.ic_image, "Ziven Louis", "Machine Learning Engineer", ""),
            Team(
                R.drawable.ic_image,
                "Nur Aisyah Aswari",
                "Cloud Computing Engineer",
                ""
            ),
            Team(R.drawable.ic_image, "Abednego Sirait", "Cloud Computing Engineer", ""),
            Team(
                R.drawable.ic_image,
                "Harry Sion Tarigan",
                "Mobile Apps Developer",
                ""
            ),
            Team(
                R.drawable.ic_image,
                "Pieter Tanoto",
                "Mobile Apps Developer",
                ""
            )

        )
    }
}
