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


class AdapterRecyclerNext(val context : Context, val event: List<Event>, val listener : (Event) -> Unit) : RecyclerView.Adapter<AdapterRecyclerNext.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.cardview_next,
                p0,
                false
            )
        )


    override fun getItemCount(): Int = event.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binItem(event[position],listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var name = view.findViewById<TextView>(R.id.tv_date)
        private var team = view.findViewById<TextView>(R.id.team)
        fun binItem(event: Event, listener: (Event) -> Unit) {
            val oldSdf = SimpleDateFormat("dd/MM/yy")
            val newSdf = SimpleDateFormat("EEEE, MMM d, yyyy", Locale.getDefault())
            val newDate = newSdf.format(oldSdf.parse(event.dateEventLocal))
            name.text = newDate
            team.text = event.strEvent
            itemView.setOnClickListener{
                listener(event)
            }

        }
    }
}