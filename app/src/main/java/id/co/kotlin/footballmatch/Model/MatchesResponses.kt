package com.matchschedules.sub.submission2_matchschedules.model

import com.google.gson.annotations.SerializedName

class MatchesResponses (
        @SerializedName("events") var events: List<Match>)