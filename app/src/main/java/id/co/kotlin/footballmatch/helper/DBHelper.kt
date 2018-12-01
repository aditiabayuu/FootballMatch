package id.co.kotlin.footballmatch.helper

import android.content.Context
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select


class DBHelper(private val context: Context) : DBRepo {


    override fun checkFavorite(eventId: String): List<FavoriteMatch> {
        return context.database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITES)
                    .whereArgs("(${FavoriteMatch.ID_EVENT} = {id})",
                            "id" to eventId)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            favorite
        }
    }


    override fun deleteData(eventId: String) {
        context.database.use {
            delete(FavoriteMatch.TABLE_FAVORITES, "(ID_EVENT = {id})",
                    "id" to eventId)
        }
    }

    override fun insertData(eventId: String, homeId: String, awayId: String,homeTeam: String,awayTeam:String, homeScore:String,AwayScore:String,date:String,homeGoal:String,awayGoal:String,homeShots:String,awayShot:String, homeGk:String, awayGk:String, homeDf : String, awayDF:String,homeMF:String,awayMf:String,homeFw: String,awayFw:String,homeSubs:String, awaySubs:String,awayFormation:String,homeFormation:String) {
        context.database.use {
            insert(FavoriteMatch.TABLE_FAVORITES,
                    FavoriteMatch.ID_EVENT to eventId,
                    FavoriteMatch.HOME_ID to homeId,
                    FavoriteMatch.AWAY_ID to awayId,
                    FavoriteMatch.HOME_TEAM to homeTeam,
                    FavoriteMatch.AWAY_TEAM to awayTeam,
                    FavoriteMatch.DATE to date,
                    FavoriteMatch.HOME_SCORE to homeScore,
                    FavoriteMatch.AWAY_SCORE to AwayScore,
                    FavoriteMatch.HOME_GOAL_DETAILS to homeGoal,
                    FavoriteMatch.AWAY_GOAL_DETAILS to awayGoal,
                    FavoriteMatch.HOME_SHOTS to homeShots,
                    FavoriteMatch.AWAY_SHOTS to awayShot,
                    FavoriteMatch.HOME_LINEUP_GOALKEEPER to homeGk,
                    FavoriteMatch.AWAY_LINEUP_GOALKEEPER to awayGk,
                    FavoriteMatch.HOME_LINEUP_DEFENSE to homeDf,
                    FavoriteMatch.AWAY_LINEUP_DEFENSE to awayDF,
                    FavoriteMatch.HOME_LINEUP_MIDFIELD to homeMF,
                    FavoriteMatch.AWAY_LINEUP_MIDFIELD to awayMf,
                    FavoriteMatch.AWAY_LINEUP_FORWARD to awayFw,
                    FavoriteMatch.HOME_LINEUP_FORWARD to homeFw,
                    FavoriteMatch.HOME_LINEUP_SUBSTITUTES to homeSubs,
                    FavoriteMatch.AWAY_LINEUP_SUBSTITUTES to awaySubs,
                    FavoriteMatch.AWAY_FORMATION to awayFormation,
                    FavoriteMatch.HOME_FORMATION to homeFormation)

        }
    }

    override fun getMatchFromDb(): List<FavoriteMatch> {
        lateinit var favoriteList: List<FavoriteMatch>
        context.database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITES)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            favoriteList = favorite
        }
        return favoriteList
    }
}