package com.matchschedules.sub.submission2_matchschedules.model

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("strTeamBadge")
        var teamBadge: String? = null,

        @SerializedName("idTeam")
        var teamId: String? = null

)