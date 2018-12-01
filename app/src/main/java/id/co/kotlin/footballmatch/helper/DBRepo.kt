package id.co.kotlin.footballmatch.helper


interface DBRepo {

    fun getMatchFromDb(): List<FavoriteMatch>

    fun insertData(eventId: String, homeId: String, awayId: String,homeTeam: String,awayTeam:String, homeScore:String,AwayScore:String,date:String,homeGoal:String,awayGoal:String,homeShots:String,awayShot:String, homeGk:String, awayGk:String, homeDf : String, awayDF:String,homeMF:String,awayMf:String,homeFw: String,awayFw:String,homeSubs:String, awaySubs:String, awayFormation:String, homeFormation:String)

    fun deleteData(eventId: String)

    fun checkFavorite(eventId: String): List<FavoriteMatch>


}