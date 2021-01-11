package com.blogspot.soyamr.arkanoidplusplus.model.game_elements

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.IGameSurface


class Paddle(private val gameSurface: IGameSurface, image: Bitmap, x: Int, y: Int) :
    GameObject(
        image, 1, 1,
        x,y
    ) {

    var paddleState: IState = State.STOPPED

    val paddleSpeed = 650

    override fun update(fps: Int) {
        paddleState.update(this, fps)

        // When the paddle touches the edge of the screen, then stop it
        if (x < 0) {
            x = 0
            paddleState = State.STOPPED
        } else if (x > gameSurface.getScreenWidth() - width) {
            x = gameSurface.getScreenWidth() - width
            paddleState = State.STOPPED
        }
    }

    override fun draw(canvas: Canvas) {
        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
    }


    fun getPaddleX() = x
    fun getPaddleY() = y
    fun setPaddleX(value: Int) {
        x = value;
    }


    val paddleRect: Rect = Rect()

    fun getRect(): Rect {
        paddleRect.set(x, y, x + width, y + height)
        return paddleRect
    }
}
