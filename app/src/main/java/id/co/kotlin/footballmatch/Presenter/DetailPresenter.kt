package id.co.kotlin.footballmatch

import com.google.gson.Gson
import com.matchschedules.sub.submission2_matchschedules.model.TeamResponses
import id.co.kotlin.footballmatch.Api.Repository
import id.co.kotlin.footballmatch.Api.TheSportDBApi
import id.co.kotlin.footballmatch.Interface.DetailView
import id.co.kotlin.footballmatch.Utils.CoroutineContextProvider
import id.co.kotlin.footballmatch.helper.DBRepo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailPresenter(private val view: DetailView,
                      private val apiRepository: Repository,
                      private val gson: Gson, private val localRepositoryImpl: DBRepo, private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getTeamDetailHome(homeTeamId: String) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamsbyId(homeTeamId)).await(),
                    TeamResponses::class.java)

            view.hideLoading()
            view.showHomeBadge(data.teams[0].teamBadge!!)

        }
    }

    fun getTeamDetailAway(homeTeamId: String) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamsbyId(homeTeamId)).await(),
                    TeamResponses::class.java)

            view.hideLoading()
            view.showAwayBadge(data.teams[0].teamBadge!!)

        }
    }

    fun deleteMatch(id: String) {
        localRepositoryImpl.deleteData(id)
    }

    fun checkMatch(id: String) {
        view.setFavoriteState(localRepositoryImpl.checkFavorite(id))
    }

    fun insertMatch(eventId: String, homeId: String, awayId: String, homeTeam: String, awayTeam: String, homeScore: String, AwayScore: String, date: String, homeGoal: String, awayGoal: String, homeShots: String, awayShot: String, homeGk: String, awayGk: String, homeDf: String, awayDF: String, homeMF: String, awayMf: String, homeFw: String, awayFw: String, homeSubs: String, awaySubs: String, awayForm: String, homeForm: String) {
        localRepositoryImpl.insertData(eventId, homeId, awayId, homeTeam, awayTeam, homeScore, AwayScore, date, homeGoal, awayGoal, homeShots, awayShot, homeGk, awayGk, homeDf, awayDF, homeMF, awayMf, homeFw, awayFw, homeSubs, awaySubs, awayForm, homeForm)
    }


}