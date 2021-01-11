package com.blogspot.soyamr.arkanoidplusplus.model.game_elements

import android.graphics.Bitmap
import android.graphics.Canvas
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.IGameSurface
import com.blogspot.soyamr.arkanoidplusplus.model.ModelBallInterface

class Star(
    private val model: ModelBallInterface,
    private val gameSurface: IGameSurface,
    image: Bitmap,
    x: Int,
    y: Int
) : GameObject(
    image,
    1,
    1,
    x, y
) {
    override fun update(fps: Int) {

    }

    override fun draw(canvas: Canvas) {
        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
    }

}