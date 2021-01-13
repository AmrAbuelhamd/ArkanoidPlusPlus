package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model

interface ModelBallInterface {
    fun reduceLive()
    fun playSoundLeft()
    fun playSoundRight()
    fun playSoundTop()
    fun playSoundBottom()
    fun pause()
}