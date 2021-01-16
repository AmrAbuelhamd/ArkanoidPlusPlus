package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.game_elements

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.util.Log
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.Model


class Paddle(private val model: Model, image: Bitmap, x: Int, y: Int) :
    GameObject(
        image, 1, 1,
        x, y
    ) {
    interface ImgState {
        var ctr: Int
        fun getImg(): Bitmap
        fun reset() {
            ctr = 0;
        }
    }

    val imgNormal = object : ImgState {
        override var ctr: Int = 0
        override fun getImg(): Bitmap {
            return model.gameBitmaps.paddleImg
        }
    }
    val imgBig = object : ImgState {
        override var ctr: Int = 0
        val threshold = 3000
        override fun getImg(): Bitmap {
            Log.i("paddle ","Big: $ctr")
            if (++ctr > threshold) {
                ctr = 0;
                imgState = imgNormal
            }
            return model.gameBitmaps.paddleImgBig
        }
    }
    val imgSmall = object : ImgState {
        override var ctr: Int = 0
        val threshold = 3000
        override fun getImg(): Bitmap {
            Log.i("paddle ","small: $ctr")
            if (++ctr > threshold) {
                ctr = 0;
                imgState = imgNormal
            }
            return model.gameBitmaps.paddleImgSmall
        }
    }

    private var imgState = imgNormal

    var paddleState: IState = State.STOPPED

    val paddleSpeed = 650

    override fun update(fps: Int) {
        paddleState.update(this, fps)

        // When the paddle touches the edge of the screen, then stop it
        if (x < 0) {
            x = 0
            paddleState = State.STOPPED
        } else if (x > model.getScreenWidth() - imgState.getImg().width) {
            x = model.getScreenWidth() - imgState.getImg().width
            paddleState = State.STOPPED
        }
    }

    override fun draw(canvas: Canvas) {
        canvas.drawBitmap(imgState.getImg(), x.toFloat(), y.toFloat(), null)
    }


    fun getPaddleX() = x
    fun getPaddleY() = y
    fun setPaddleX(value: Int) {
        x = value;
    }


    private val paddleRect: Rect = Rect()

    fun getRect(): Rect {
        paddleRect.set(x, y, x + imgState.getImg().width, y + imgState.getImg().height)
        return paddleRect
    }

    fun reset() {
        x = model.getScreenWidth() / 4
        y = model.getScreenHeight() - model.dimensions.paddleInitialYPosition
    }

    fun changePaddleImgState(state: ImgState) {
        imgState.reset()
        imgState = state
    }

}
