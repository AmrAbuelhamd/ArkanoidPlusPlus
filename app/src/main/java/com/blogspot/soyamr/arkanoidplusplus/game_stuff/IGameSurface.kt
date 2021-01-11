package com.blogspot.soyamr.arkanoidplusplus.game_stuff

interface IGameSurface {
    fun getScreenWidth(): Int
    fun getScreenHeight(): Int
    fun setPaused(paused: Boolean)
    fun startScoreActivity(score: Int)
}
