package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model

import com.blogspot.soyamr.arkanoidplusplus.game_stuff.Dimensions
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.game_elements.Ball

interface ModelBallInterface {
    val dimensions: Dimensions

    fun reduceLive()
    fun playSoundLeft()
    fun playSoundRight()
    fun playSoundTop()
    fun playSoundBottom()
    fun pause()
}