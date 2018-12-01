package com.matchschedules.sub.submission2_matchschedules.main

import com.google.gson.Gson
import com.matchschedules.sub.submission2_matchschedules.model.MatchesResponses
import id.co.kotlin.footballmatch.Api.Repository
import id.co.kotlin.footballmatch.Api.TheSportDBApi
import id.co.kotlin.footballmatch.Interface.MainView
import id.co.kotlin.footballmatch.Utils.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: Repository,
                    private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getNextEventList(league: String) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextMatches(league)).await(),
                    MatchesResponses::class.java)
                view.hideLoading()
                view.showEventList(data.events)

        }
    }

    fun getPastEventList( league:String) {
        view.showLoading()
        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getPastMatches(league)).await(),
                    MatchesResponses::class.java)

                view.hideLoading()
                view.showEventList(data.events)

        }
    }
}