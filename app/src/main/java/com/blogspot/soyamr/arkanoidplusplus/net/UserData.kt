package com.blogspot.soyamr.arkanoidplusplus.net

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserData(
    var nickname: String = "",
    var score: Int = 0,
    var alive: Boolean = false,
    var icon: Int = 0,
    var levels: Int = 0
)