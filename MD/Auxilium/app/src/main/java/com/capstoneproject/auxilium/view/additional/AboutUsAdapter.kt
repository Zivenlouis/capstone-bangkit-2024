package com.capstoneproject.auxilium.view.additional

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstoneproject.auxilium.databinding.ItemAboutUsBinding

class AboutUsAdapter(private val teamsList: List<Team>) : RecyclerView.Adapter<AboutUsAdapter.TeamViewHolder>() {

    class TeamViewHolder(val binding: ItemAboutUsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAboutUsBinding.inflate(inflater, parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teamsList[position]
        holder.binding.ivProfileTeams.setImageResource(team.imageResource)
        holder.binding.tvProfileNames.text = team.name
        holder.binding.tvProfileRoles.text = team.role
        holder.binding.tvProfileQuotes.text = team.quote
    }

    override fun getItemCount(): Int {
        return teamsList.size
    }
}
