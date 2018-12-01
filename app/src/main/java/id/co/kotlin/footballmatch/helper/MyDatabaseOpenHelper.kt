package id.co.kotlin.footballmatch.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*


class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context) = instance ?: MyDatabaseOpenHelper(ctx)
    }

    override fun onCreate(p0: SQLiteDatabase) {
        // Here you create tables
        p0.createTable(FavoriteMatch.TABLE_FAVORITES, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.ID_EVENT to TEXT,
                FavoriteMatch.DATE to TEXT,

                // home team
                FavoriteMatch.HOME_ID to TEXT,
                FavoriteMatch.HOME_TEAM to TEXT,
                FavoriteMatch.HOME_SCORE to TEXT,
                FavoriteMatch.HOME_FORMATION to TEXT,
                FavoriteMatch.HOME_GOAL_DETAILS to TEXT,
                FavoriteMatch.HOME_SHOTS to TEXT,
                FavoriteMatch.HOME_LINEUP_GOALKEEPER to TEXT,
                FavoriteMatch.HOME_LINEUP_DEFENSE to TEXT,
                FavoriteMatch.HOME_LINEUP_MIDFIELD to TEXT,
                FavoriteMatch.HOME_LINEUP_FORWARD to TEXT,
                FavoriteMatch.HOME_LINEUP_SUBSTITUTES to TEXT,

                // away team
                FavoriteMatch.AWAY_ID to TEXT,
                FavoriteMatch.AWAY_TEAM to TEXT,
                FavoriteMatch.AWAY_SCORE to TEXT,
                FavoriteMatch.AWAY_FORMATION to TEXT,
                FavoriteMatch.AWAY_GOAL_DETAILS to TEXT,
                FavoriteMatch.AWAY_SHOTS to TEXT,
                FavoriteMatch.AWAY_LINEUP_GOALKEEPER to TEXT,
                FavoriteMatch.AWAY_LINEUP_DEFENSE to TEXT,
                FavoriteMatch.AWAY_LINEUP_MIDFIELD to TEXT,
                FavoriteMatch.AWAY_LINEUP_FORWARD to TEXT,
                FavoriteMatch.AWAY_LINEUP_SUBSTITUTES to TEXT)

    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.dropTable(FavoriteMatch.TABLE_FAVORITES, true)
     }
}
// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)