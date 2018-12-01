package id.co.kotlin.footballmatch.Interface

import com.matchschedules.sub.submission2_matchschedules.model.Match

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Match>)
    fun showError(message: String)
}