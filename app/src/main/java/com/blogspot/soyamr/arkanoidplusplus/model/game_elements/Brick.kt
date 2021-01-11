package com.blogspot.soyamr.arkanoidplusplus.model.game_elements

import android.graphics.*
import com.blogspot.soyamr.arkanoidplusplus.IGameSurface

class Brick(
    private val gameSurface: IGameSurface,
    image: Bitmap,
    row: Int,
    col: Int,
    brickWidth: Int,
    brickHeight: Int
) :
    GameObject(
        image, 1, 1,
        col * brickWidth + 10,
        row * brickHeight + 10
    ) {
    public val rect = Rect()
    val paint = Paint()

    init {
        rect.set(x, y, x + brickWidth - 20, y + brickHeight - 20)
        paint.color = Color.RED
    }

    var padding = 1
    override fun update(fps: Int) {
//        TODO("Not yet implemented")
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRect(rect, paint)
    }

    fun setInvisible() {
        isVisible = false
    }

    fun getVisibility(): Boolean {
        return isVisible
    }

}