package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model

import android.graphics.Canvas

interface ILevel {
    val levelNum: Int
    fun initiateLevel(model: Model)
    fun setNextLevel(model: Model)

    fun showWinButtons(model: Model, canvas: Canvas) {
        canvas.drawText(
            model.nextLevelText,
            model.nextLevelButtonX.toFloat(),
            model.nextLevelButtonY.toFloat(),
            model.paint
        )
        canvas.drawText(
            model.mainMenuText,
            model.mainMenuButtonX.toFloat(),
            model.mainMenuButtonY.toFloat(),
            model.paint
        )
    }

    fun showLoseButtons(model: Model, canvas: Canvas) {
        canvas.drawText(
            model.mainMenuText,
            model.mainMenuButtonX.toFloat(),
            model.mainMenuButtonY.toFloat(),
            model.paint
        )
    }
}