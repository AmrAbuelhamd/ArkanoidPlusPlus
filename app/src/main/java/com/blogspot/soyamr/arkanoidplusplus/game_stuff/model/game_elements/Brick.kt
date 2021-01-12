package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.game_elements

import android.graphics.*
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.IGameSurface

class Brick(
    private val gameSurface: IGameSurface,
    image: Bitmap,
    x: Int,
    y: Int
) :
    GameObject(
        image, 1, 1,
        x, y
    ) {
    public val rect = Rect()
    val paint = Paint()

    init {
        rect.set(
            x, y,
            x + image.width,
            y + image.height
        )
        paint.color = Color.RED
    }

    override fun update(fps: Int) {
//        TODO("Not yet implemented")
    }

    override fun draw(canvas: Canvas) {
        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
    }

    fun setInvisible() {
        isVisible = false
    }

    fun getVisibility(): Boolean {
        return isVisible
    }

}