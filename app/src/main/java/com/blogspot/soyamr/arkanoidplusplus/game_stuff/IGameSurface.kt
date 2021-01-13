package com.blogspot.soyamr.arkanoidplusplus.game_stuff

interface IGameSurface {
    fun getScreenWidth(): Int
    fun getScreenHeight(): Int
    fun showMainMenu()
    fun hereIsUserScores(score: Int, levelNum: Int)
}
