package id.co.kotlin.footballmatch.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.matchschedules.sub.submission2_matchschedules.model.Match
import id.co.kotlin.footballmatch.R
import org.jetbrains.anko.find

class MainAdapter(private val context: Context, private val events: List<Match>, val listener: (Match) -> Unit): RecyclerView.Adapter<EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
            EventViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))


    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }
}

class EventViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val homeTeam: TextView = view.find(R.id.homenameTV)
    private val eventDate: TextView = view.find(R.id.dateeventTV)
    private val homeScore: TextView = view.find(R.id.homescoreTV)
    private val awayScore: TextView = view.find(R.id.awayscoreTV)
    private val awayTeam: TextView = view.find(R.id.awaynameTV)
    fun bindItem(events: Match, listener: (Match) -> Unit) {
        homeTeam.text = events.strHomeTeam
        eventDate.text = events.dateEvent
        homeScore.text = events.intHomeScore
        awayScore.text = events.intAwayScore
        awayTeam.text = events.strAwayTeam
        itemView.setOnClickListener {
            listener(events)
        }
    }

}