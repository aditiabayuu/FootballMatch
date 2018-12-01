package id.co.kotlin.footballmatch

import android.util.Log
import com.google.gson.Gson
import com.matchschedules.sub.submission2_matchschedules.main.MainPresenter
import com.matchschedules.sub.submission2_matchschedules.model.Match
import com.matchschedules.sub.submission2_matchschedules.model.MatchesResponses
import id.co.kotlin.footballmatch.Api.Repository
import id.co.kotlin.footballmatch.Api.TheSportDBApi
import id.co.kotlin.footballmatch.Interface.MainView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Created by root on 3/1/18.
 */
class ApiRepositoryTest {
    @Mock
    private
    lateinit var view: MainView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: Repository

    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    @Throws(Exception::class)
    fun testPastMatches() {
        val teams: MutableList<Match> = mutableListOf()
        val response = MatchesResponses(teams)
        when{
            teams.isNotEmpty() ->
                GlobalScope.launch {

                    `when`(gson.fromJson(apiRepository
                            .doRequest(TheSportDBApi.getPastMatches("4332")).await(),
                            MatchesResponses::class.java
                    )).thenReturn(response)

                    presenter.getPastEventList("4332")

                    Mockito.verify(view).showLoading()
                    Mockito.verify(view).showEventList(teams)
                    Mockito.verify(view).hideLoading()
                    Mockito.verify(view, Mockito.never()).showError("No Data")


                }
            else->
                GlobalScope.launch {
                   `when`(gson.fromJson(apiRepository
                            .doRequest(TheSportDBApi.getPastMatches("4332")).await(),
                            MatchesResponses::class.java
                    )).thenReturn(response)
                    Mockito.verify(view).showLoading()
                    Log.d("tag","no intenrnet")
                    Mockito.verify(view).hideLoading()
                    Mockito.verify(view).showError("no Data")
                    Mockito.verify(view, Mockito.never()).showEventList(teams)

                }
   }

    }

    @Test
    @Throws(Exception::class)
    fun getEventReturnsError() {
        val teams: MutableList<Match> = mutableListOf()
        val response = MatchesResponses(teams)
        GlobalScope.launch {

            `when`(gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getPastMatches("433432432")).await(),
                    MatchesResponses::class.java
            )).thenReturn(response)

            presenter.getPastEventList("433432432")

            Mockito.verify(view).showLoading()
            Mockito.verify(view).hideLoading()
            Mockito.verify(view).showError("no Data")
            Mockito.verify(view, Mockito.never()).showEventList(teams)
        }
    }
}