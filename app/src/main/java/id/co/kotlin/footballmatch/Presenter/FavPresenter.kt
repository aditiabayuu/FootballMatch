package id.co.kotlin.footballmatch.Presenter


import android.content.Context
import id.co.kotlin.footballmatch.Interface.FavMainView
import id.co.kotlin.footballmatch.helper.FavoriteMatch
import id.co.kotlin.footballmatch.helper.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavPresenter(val mView: FavMainView) {


    fun getfavoriteMatch(context: Context) {
        mView.showLoading()

        context.database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITES)
            val fav = result.parseList(classParser<FavoriteMatch>())
            mView.showEventList(fav)
            mView.hideLoading()
        }
    }


}