package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.game_elements

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.IGameSurface
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.ModelBallInterface
import java.util.*

enum class Direction {
    DOWN_RIGHT, DOWN_LEFT, UP_RIGHT, UP_LEFT,
}

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
    enum class Side { LEFT, RIGHT, TOP, BOTTOM }

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


    private val xVelocityDefault = 400;
    private val yVelocityDefault = 800;
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
        yVelocity = -yVelocity//400 800
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
        this.y = y - height - 2
    }

    private fun clearObstacleX(x: Int) {
        this.x = x
    }

    fun reset() {
        this.x = model.dimensions.screenWidth / 2
        this.y =
            model.dimensions.screenHeight - model.dimensions.paddleInitialYPosition - model.dimensions.ballHeight
        xVelocity = xVelocityDefault
        yVelocity = -yVelocityDefault
    }

    private val _ballRect = Rect()
    private val ballRect: Rect
        get() {
            _ballRect.set(x, y, x + width, y + height)
            return _ballRect
        }

    fun intersects(rect: Rect): Boolean {
        return Rect.intersects(ballRect, rect)
    }

    fun decideBallNewVelocityAccordingToPaddle(paddleState: IState, paddle: Rect) {
        when (ballHitWhichSide(paddle)) {
            Side.RIGHT -> {
                clearObstacleX(paddle.right + 2)
                reverseXVelocity()
                return
            }
            Side.LEFT -> {
                clearObstacleX(paddle.left + -width - 2)
                reverseXVelocity()
                return
            }
            else -> {
                val direction = getBallDirection()
                adjustAngel(paddle)
                //anyway i should bounce it back up
                reverseYVelocity()
                when (paddleState) {
                    State.LEFT ->
                        if (direction == Direction.DOWN_RIGHT) {
                            reverseXVelocity()
                        }
                    State.RIGHT ->
                        if (direction == Direction.DOWN_LEFT) {
                            reverseXVelocity()
                        }
                    State.STOPPED ->
                        if (direction == Direction.DOWN_RIGHT && ballRect.centerX() < paddle.centerX()) {
                            reverseXVelocity()
                        } else if (direction == Direction.DOWN_LEFT && ballRect.centerX() > paddle.centerX()) {
                            reverseXVelocity()
                        }
                }
                clearObstacleY(paddle.top);
            }
        }
    }

    fun decideBallNewVelocityAccordingToBrick(brick: Rect) {
        when (ballHitWhichSide(brick)) {
            Side.TOP, Side.BOTTOM -> reverseYVelocity()
            Side.LEFT, Side.RIGHT -> reverseXVelocity()
        }
    }

    private fun ballHitWhichSide(rect: Rect): Side {
        val ballRect = ballRect
        val wy = (ballRect.width() + rect.width()) * (ballRect.centerY() - rect.centerY());
        val hx = (ballRect.height() + rect.height()) * (ballRect.centerX() - rect.centerX());

        return if (wy > hx) {
            if (wy > -hx) {
                Side.TOP
            } else {
                Side.LEFT
            }
        } else {
            if (wy > -hx) {
                Side.RIGHT
            } else {
                Side.BOTTOM
            }
        }
    }

    private fun getBallDirection(): Direction {
        //    -,-                                                           -,+
        return if (xVelocity < 0 && yVelocity < 0) Direction.UP_LEFT else if (xVelocity < 0 && yVelocity > 0) Direction.DOWN_LEFT
        //   +,-                                            //+,+
        else if (xVelocity > 0 && yVelocity < 0) Direction.UP_RIGHT else Direction.DOWN_RIGHT
                                                            //if(xVelocity < 0 && yVelocity > 0)
    }

    private fun adjustAngel(paddle: Rect) {
        val part = paddle.width() / 10;
        val a = paddle.centerX() - part / 2
        val b = paddle.centerX() + part / 2
        val xDir = if (xVelocity < 0) -1 else 1
        val yDir = if (yVelocity < 0) -1 else 1
        if (ballRect.centerX() in (a + 1) until b) {
            xVelocity = 0;
            yVelocity = 800
        } else if (ballRect.centerX() < (paddle.left + part) ||
            ballRect.centerX() > (paddle.right - part + 1)
        ) {
            yVelocity = yDir * 400
            xVelocity = xDir * 800
        } else if (ballRect.centerX() in (paddle.left + part + 1) until (paddle.right - part)) {
            yVelocity = yDir * yVelocityDefault
            xVelocity = xDir * xVelocityDefault
        }
    }
}