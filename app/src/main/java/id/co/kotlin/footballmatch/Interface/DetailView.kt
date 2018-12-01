package id.co.kotlin.footballmatch.Interface

import id.co.kotlin.footballmatch.helper.FavoriteMatch

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showHomeBadge (homeBadge: String)
    fun showAwayBadge (awayBadge: String)
    fun setFavoriteState(favList: List<FavoriteMatch>)
}