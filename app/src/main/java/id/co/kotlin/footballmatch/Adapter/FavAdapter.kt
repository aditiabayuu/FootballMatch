package id.co.kotlin.footballmatch.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import id.co.kotlin.footballmatch.R
import id.co.kotlin.footballmatch.helper.FavoriteMatch
import org.jetbrains.anko.find

class FavAdapter(private val context: Context, private val events: List<FavoriteMatch>, val listener: (FavoriteMatch) -> Unit) : RecyclerView.Adapter<EventFavViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            EventFavViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventFavViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }
}

class EventFavViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val homeTeam: TextView = view.find(R.id.homenameTV)
    private val eventDate: TextView = view.find(R.id.dateeventTV)
    private val homeScore: TextView = view.find(R.id.homescoreTV)
    private val awayScore: TextView = view.find(R.id.awayscoreTV)
    private val awayTeam: TextView = view.find(R.id.awaynameTV)
    fun bindItem(events: FavoriteMatch, listener: (FavoriteMatch) -> Unit) {
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