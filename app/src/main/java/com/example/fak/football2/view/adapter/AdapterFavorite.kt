package com.example.fak.football2.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fak.football2.R
import com.example.fak.football2.model.FavoriteData
import java.text.SimpleDateFormat
import java.util.*

class AdapterFavorite (val context: Context, val fav: List<FavoriteData>, val listener: (String?) -> Unit)
    : RecyclerView.Adapter<AdapterFavorite.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        AdapterFavorite.ViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_last,p0,false
            )
        )

    override fun getItemCount(): Int = fav.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.binItem(fav[p1],listener)
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var name = view.findViewById<TextView>(R.id.tv_date)
        private var teamHome = view.findViewById<TextView>(R.id.team_home)
        private var teamAway = view.findViewById<TextView>(R.id.team_away)
        private var scoreHome = view.findViewById<TextView>(R.id.score_home)
        private var scoreAway = view.findViewById<TextView>(R.id.score_away)
        fun binItem(fav: FavoriteData, listener: (String?) -> Unit) {
            val oldSdf = SimpleDateFormat("yyyy-MM-dd")
            val newSdf = SimpleDateFormat("EEEE, MMM d, yyyy", Locale.getDefault())
            val newDate = newSdf.format(oldSdf.parse(fav.date))
            name.text = newDate
            teamHome.text = fav.homeName
            teamAway.text = fav.awayName
            scoreAway.text = fav.awayGoal
            scoreHome.text = fav.homeGoal
            itemView.setOnClickListener{
                listener(fav.eventId)
            }

        }
    }
}