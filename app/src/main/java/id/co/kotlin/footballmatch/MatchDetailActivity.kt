package id.co.kotlin.footballmatch

import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat

import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import android.widget.ImageView

import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson


import com.matchschedules.sub.submission2_matchschedules.detail.DetailPresenter
import com.matchschedules.sub.submission2_matchschedules.model.Match
import id.co.kotlin.footballmatch.Api.Repository

import id.co.kotlin.footballmatch.Interface.DetailView
import id.co.kotlin.footballmatch.Utils.invisible
import id.co.kotlin.footballmatch.Utils.visible
import id.co.kotlin.footballmatch.helper.DBHelper
import id.co.kotlin.footballmatch.helper.FavoriteMatch
import kotlinx.android.synthetic.main.detail_activity.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.toast


class MatchDetailActivity : AppCompatActivity(), DetailView {

    private lateinit var presenter: DetailPresenter
    private lateinit var progressBar: ProgressBar
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var event: Match

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        progressBar = findViewById (R.id.progress)
        showActionBar()
        event = intent.getParcelableExtra("match")
        val request = Repository()
        val gson = Gson()
        val localRepo = DBHelper(applicationContext)
        presenter = DetailPresenter(this, request, gson,localRepo)
        presenter.getTeamDetailHome(event.idHomeTeam)
        presenter.getTeamDetailAway(event.idAwayTeam)
        presenter.checkMatch(event.idEvent!!)
        initData(event)



    }
    private fun initData(matchEvent: Match) {
        val textMatchDate = findViewById<TextView>(R.id.matchdate)
        val textHomeTeam =findViewById<TextView>(R.id.txt_club_home)
        val textAwayTeam = findViewById<TextView>(R.id.txt_club_away)
        val textHomeScore =findViewById<TextView>(R.id.teamhomescore)
        val textAwayScore =findViewById<TextView>(R.id.teamawayscore)
        val textHomeGoals = findViewById<TextView>(R.id.txt_home_goal)
        val textAwayGoals = findViewById<TextView>(R.id.txt_away_goal)

        val textHomeGK = findViewById<TextView>(R.id.txt_home_gk)
        val textAwayGK = findViewById<TextView>(R.id.txt_away_gk)

        val textHomeDF = findViewById<TextView>(R.id.txt_home_df)
        val textAwayDF = findViewById<TextView>(R.id.txt_away_df)

        val textHomeMF = findViewById<TextView>(R.id.txt_home_mf)
        val textAwayMF = findViewById<TextView>(R.id.txt_away_mf)

        val textHomeFW = findViewById<TextView>(R.id.txt_home_fw)
        val textAwayFW = findViewById<TextView>(R.id.txt_away_fw)

        val textHomeSubs = findViewById<TextView>(R.id.txt_home_subs)
        val textAwaySubs = findViewById<TextView>(R.id.txt_away_subs)

        textMatchDate.text = matchEvent.dateEvent
        textHomeScore.text = matchEvent.intHomeScore
        textAwayScore.text = matchEvent.intAwayScore
        textAwayTeam.text= matchEvent.strAwayTeam
        textHomeTeam.text=matchEvent.strHomeTeam
        txt_home_shots.text = matchEvent.intHomeShots
        txt_away_shots.text = matchEvent.intAwayShots
        textHomeGoals.text = matchEvent.strHomeGoalDetails
        textAwayGoals.text = matchEvent.strAwayGoalDetails
        textHomeGK.text = matchEvent.strHomeLineupGoalkeeper
        textAwayGK.text = matchEvent.strAwayLineupGoalkeeper
        textHomeDF.text = matchEvent.strHomeLineupDefense
        textAwayDF.text = matchEvent.strAwayLineupDefense
        textHomeMF.text = matchEvent.strHomeLineupMidfield
        textAwayMF.text = matchEvent.strAwayLineupMidfield
        textHomeFW.text = matchEvent.strHomeLineupForward
        textAwayFW.text = matchEvent.strAwayLineupForward
        textHomeSubs.text = matchEvent.strHomeLineupSubstitutes
        textAwaySubs.text = matchEvent.strAwayLineupSubstitutes

    }
    private fun showActionBar(){
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
    }




    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showHomeBadge(homeBadge: String) {
        val homeImage = findViewById<ImageView>(R.id.logo_home)
        Glide.with(this).load(homeBadge).into(homeImage)
    }
    override fun showAwayBadge(awayBadge: String) {
        val homeImage = findViewById<ImageView>(R.id.logo_away)
        Glide.with(this).load(awayBadge).into(homeImage)
    }

    override fun onSupportNavigateUp() : Boolean{
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.favorites -> {
                if (!isFavorite) {
                    presenter.insertMatch(
                            event.idEvent!!, event.idHomeTeam, event.idAwayTeam, event.strHomeTeam!!,event.strAwayTeam!!,event.intHomeScore!!,event.intAwayScore!!,event.dateEvent!!,event.strHomeGoalDetails!!,event.strAwayGoalDetails!!,event.intHomeShots!!,event.strAwayGoalDetails!!,event.strHomeLineupGoalkeeper!!,event.strAwayLineupGoalkeeper!!,event.strHomeLineupDefense!!,event.strAwayLineupDefense!!,event.strHomeLineupMidfield!!,event.strAwayLineupMidfield!!,event.strHomeLineupForward!!,event.strAwayLineupForward!!,event.strHomeLineupSubstitutes!!,event.strAwayLineupSubstitutes!!,event.strAwayFormation!!,event.strHomeFormation!!)
                    toast("Event added to favorite")
                    isFavorite = !isFavorite
                } else {
                    presenter.deleteMatch(event.idEvent!!)
                    toast("Event removed favorite")
                    isFavorite = !isFavorite
                }
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_fav_24dp)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_fav_24dp)
    }

    override fun setFavoriteState(favList: List<FavoriteMatch>) {
        if (!favList.isEmpty()) {
            isFavorite = true
        }
    }


}


