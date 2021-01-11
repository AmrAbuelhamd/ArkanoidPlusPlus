package com.blogspot.soyamr.arkanoidplusplus.model.game_elements

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.IGameSurface
import com.blogspot.soyamr.arkanoidplusplus.model.Model
import com.blogspot.soyamr.arkanoidplusplus.model.ModelBallInterface
import java.util.*


class Ball(
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

    constructor(model: Model, gameSurface: IGameSurface, image: Bitmap) : this(
        model,
        gameSurface,
        image,
        gameSurface.getScreenWidth() / 2,
        gameSurface.getScreenHeight() - 200
    )

    private val ROW_TOP_TO_BOTTOM = 0
    private val ROW_LEFT_TO_RIGHT = 1
    private val ROW_BOTTOM_TO_TOP = 2
    private val ROW_RIGHT_TO_LEFT = 3
    private val generator = Random()

    // Row index of Image are being used.
    private var rowUsing = ROW_BOTTOM_TO_TOP

    private var colUsing = 0

    private var leftToRights: Array<Bitmap?> = arrayOfNulls(colCount)
    private var rightToLefts: Array<Bitmap?> = arrayOfNulls(colCount)
    private var topToBottoms: Array<Bitmap?> = arrayOfNulls(colCount)
    private var bottomToTops: Array<Bitmap?> = arrayOfNulls(colCount)


    var xVelocity = 400;
    var yVelocity = -800;

    init {
//        for (col in 0 until colCount) {
//            topToBottoms[col] = createSubImageAt(ROW_TOP_TO_BOTTOM, col)
//            rightToLefts[col] = createSubImageAt(ROW_RIGHT_TO_LEFT, col)
//            leftToRights[col] = createSubImageAt(ROW_LEFT_TO_RIGHT, col)
//            bottomToTops[col] = createSubImageAt(ROW_BOTTOM_TO_TOP, col)
//        }
    }

    private fun getMoveBitmaps(): Array<Bitmap?>? {
        return when (rowUsing) {
            ROW_BOTTOM_TO_TOP -> bottomToTops
            ROW_LEFT_TO_RIGHT -> leftToRights
            ROW_RIGHT_TO_LEFT -> rightToLefts
            ROW_TOP_TO_BOTTOM -> topToBottoms
            else -> null
        }
    }

    private fun getCurrentMoveBitmap(): Bitmap? {
        val bitmaps = getMoveBitmaps()
        return bitmaps!![colUsing]
    }


    override fun update(fps: Int) {
//        ++colUsing
//        if (colUsing >= colCount) {
//            colUsing = 0
//        }

        x += (xVelocity / fps)
        y += (yVelocity / fps)

        // When the game's character touches the edge of the screen, then change direction
        if (x < 0) {
            x = 0
            reverseXVelocity()
            model.playSoundLeft()
        } else if (x > gameSurface.getScreenWidth() - width) {
            clearObstacleX(gameSurface.getScreenWidth() - width)
            reverseXVelocity()
            model.playSoundRight()
        }
        if (y < 0) {
            y = 0
            reverseYVelocity()
            model.playSoundTop()
        } else if (y > gameSurface.getScreenHeight() - height) {
            reset()
            reverseYVelocity()
            model.pause()
            model.reduceLive()
            model.playSoundBottom()
        }

        // rowUsing
//        if (xVelocity > 0) {
//            rowUsing = if (yVelocity > 0 && abs(xVelocity) < abs(yVelocity)) {
//                ROW_TOP_TO_BOTTOM
//            } else if (yVelocity < 0 && abs(xVelocity) < abs(yVelocity)) {
//                ROW_BOTTOM_TO_TOP
//            } else {
//                ROW_LEFT_TO_RIGHT
//            }
//        } else {
//            rowUsing = if (yVelocity > 0 && abs(xVelocity) < abs(yVelocity)) {
//                ROW_TOP_TO_BOTTOM
//            } else if (yVelocity < 0 && abs(xVelocity) < abs(yVelocity)) {
//                ROW_BOTTOM_TO_TOP
//            } else {
//                ROW_RIGHT_TO_LEFT
//            }
//        }
    }

    override fun draw(canvas: Canvas) {
//        val bitmap = getCurrentMoveBitmap()
//        canvas.drawBitmap(bitmap!!, x.toFloat(), y.toFloat(), null)
        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
    }

    fun reverseYVelocity() {
        yVelocity = -yVelocity
    }

    fun reverseXVelocity() {
        xVelocity = -xVelocity
    }

    fun setRandomXVelocity() {
        val answer: Int = generator.nextInt(2)
        if (answer == 0) {
            reverseXVelocity()
        }
    }

    fun clearObstacleY(y: Int) {
        this.y = y - height
    }

    fun clearObstacleX(x: Int) {
        this.x = x
    }

    fun reset() {
        this.x = gameSurface.getScreenWidth() / 2
        this.y = gameSurface.getScreenHeight() - 200
    }

    val ballRect = Rect();
    fun intersects(rect: Rect): Boolean {
        ballRect.set(x, y, x + width, y + height)
        return Rect.intersects(ballRect, rect)
    }

}