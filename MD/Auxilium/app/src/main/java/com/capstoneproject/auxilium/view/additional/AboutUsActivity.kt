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
                R.drawable.profile_filbert_wijaya,
                "Filbert Wijaya",
                "Machine Learning Engineer",
                "Stay wired to innovate, stay wireless to dream."
            ),
            Team(
                R.drawable.profile_jason_tjoa,
                "Jason Tjoa",
                "Machine Learning Engineer",
                "In a world of bytes and pixels, dare to innovate beyond boundaries."
            ),
            Team(
                R.drawable.profile_ziven_louis,
                "Ziven Louis",
                "Machine Learning Engineer",
                "From gadgets to goals, elevate every aspect of your journey."
            ),
            Team(
                R.drawable.profile_nur_aisyah_aswari,
                "Nur Aisyah Aswari",
                "Cloud Computing Engineer",
                "Design your life with the precision of a UI/UX expert."
            ),
            Team(
                R.drawable.profile_abednego_sirait,
                "Abednego Sirait",
                "Cloud Computing Engineer",
                "In tech we trust, in lifestyle we thrive."
            ),
            Team(
                R.drawable.profile_harry_sion_tarigan,
                "Harry Sion Tarigan",
                "Mobile Apps Developer",
                "Tech is the canvas; creativity is your brushstroke."
            ),
            Team(
                R.drawable.profile_pieter_tanoto,
                "Pieter Tanoto",
                "Mobile Apps Developer",
                "Explore the digital frontier with fearless curiosity."
            )

        )
    }
}
