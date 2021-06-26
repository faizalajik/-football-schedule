package com.example.fak.football2.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fak.football2.R
import com.example.fak.football2.model.Event
import java.text.SimpleDateFormat
import java.util.*

class AdapterRecyclerLast(val context: Context, val event: List<Event>, val listener: (String?) -> Unit) :
    RecyclerView.Adapter<AdapterRecyclerLast.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.cardview_last,
                p0,
                false
            )
        )


    override fun getItemCount(): Int = event.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binItem(event[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var name = view.findViewById<TextView>(R.id.tv_date)
        private var teamHome = view.findViewById<TextView>(R.id.team_home)
        private var teamAway = view.findViewById<TextView>(R.id.team_away)
        private var scoreHome = view.findViewById<TextView>(R.id.score_home)
        private var scoreAway = view.findViewById<TextView>(R.id.score_away)
        fun binItem(event: Event, listener: (String?) -> Unit) {
            val oldSdf = SimpleDateFormat("yyyy-MM-dd")
            val newSdf = SimpleDateFormat("EEEE, MMM d, yyyy", Locale.getDefault())
            val newDate = newSdf.format(oldSdf.parse(event.dateEventLocal))
            name.text = newDate
            teamHome.text = event.strHomeTeam
            teamAway.text = event.strAwayTeam
            scoreAway.text = event.intAwayScore
            scoreHome.text = event.intHomeScore
            itemView.setOnClickListener{
                listener(event.idEvent)
            }

        }
    }
}