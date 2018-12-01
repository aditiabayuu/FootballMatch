package id.co.kotlin.footballmatch.Interface

import id.co.kotlin.footballmatch.helper.FavoriteMatch

interface FavMainView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<FavoriteMatch>)
}