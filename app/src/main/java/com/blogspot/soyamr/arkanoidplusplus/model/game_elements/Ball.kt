package com.blogspot.soyamr.arkanoidplusplus.model.game_elements

import android.graphics.Bitmap
import android.graphics.Canvas
import com.blogspot.soyamr.arkanoidplusplus.IGameSurface
import kotlin.math.abs


class Ball : GameObject {

    private val ROW_TOP_TO_BOTTOM = 0
    private val ROW_LEFT_TO_RIGHT = 1
    private val ROW_BOTTOM_TO_TOP = 2
    private val ROW_RIGHT_TO_LEFT = 3

    // Row index of Image are being used.
    private var rowUsing = ROW_LEFT_TO_RIGHT

    private var colUsing = 0

    private var leftToRights: Array<Bitmap?>
    private var rightToLefts: Array<Bitmap?>
    private var topToBottoms: Array<Bitmap?>
    private var bottomToTops: Array<Bitmap?>

    // Velocity of game character (pixel/millisecond)
    val VELOCITY = .3f

    private var movingVectorX = 10
    private var movingVectorY = 5

    private var lastDrawNanoTime: Long = -1

    private val gameSurface: IGameSurface

    constructor(gameSurface: IGameSurface, image: Bitmap, x: Int, y: Int) : super(
        image,
        4,
        4,
        x,
        y
    ) {
        this.gameSurface = gameSurface
        topToBottoms = arrayOfNulls(colCount)
        rightToLefts = arrayOfNulls(colCount)
        leftToRights = arrayOfNulls(colCount)
        bottomToTops = arrayOfNulls(colCount)
        for (col in 0 until colCount) {
            topToBottoms[col] = createSubImageAt(ROW_TOP_TO_BOTTOM, col)
            rightToLefts[col] = createSubImageAt(ROW_RIGHT_TO_LEFT, col)
            leftToRights[col] = createSubImageAt(ROW_LEFT_TO_RIGHT, col)
            bottomToTops[col] = createSubImageAt(ROW_BOTTOM_TO_TOP, col)
        }
    }

    fun getMoveBitmaps(): Array<Bitmap?>? {
        return when (rowUsing) {
            ROW_BOTTOM_TO_TOP -> bottomToTops
            ROW_LEFT_TO_RIGHT -> leftToRights
            ROW_RIGHT_TO_LEFT -> rightToLefts
            ROW_TOP_TO_BOTTOM -> topToBottoms
            else -> null
        }
    }

    fun getCurrentMoveBitmap(): Bitmap? {
        val bitmaps = getMoveBitmaps()
        return bitmaps!![colUsing]
    }


    override fun update() {
        colUsing++
        if (colUsing >= colCount) {
            colUsing = 0
        }
        // Current time in nanoseconds
        val now = System.nanoTime()

        // Never once did draw.
        if (lastDrawNanoTime == -1L) {
            lastDrawNanoTime = now
        }
        // Change nanoseconds to milliseconds (1 nanosecond = 1000000 milliseconds).
        val deltaTime = ((now - lastDrawNanoTime) / 1000000).toInt()

        // Distance moves
        val distance = VELOCITY * deltaTime
        val movingVectorLength =
            Math.sqrt((movingVectorX * movingVectorX + movingVectorY * movingVectorY).toDouble())

        // Calculate the new position of the game character.
        x = x + (distance * movingVectorX / movingVectorLength).toInt()
        y = y + (distance * movingVectorY / movingVectorLength).toInt()

        // When the game's character touches the edge of the screen, then change direction
        if (x < 0) {
            x = 0
            movingVectorX = -movingVectorX
        } else if (x > gameSurface.getWidth() - width) {
            x = gameSurface.getWidth() - width
            movingVectorX = -movingVectorX
        }
        if (y < 0) {
            y = 0
            movingVectorY = -movingVectorY
        } else if (y > gameSurface.getHeight() - height) {
            y = gameSurface.getHeight() - height
            movingVectorY = -movingVectorY
        }

        // rowUsing
        if (movingVectorX > 0) {
            if (movingVectorY > 0 && abs(movingVectorX) < abs(movingVectorY)) {
                rowUsing = ROW_TOP_TO_BOTTOM
            } else if (movingVectorY < 0 && abs(movingVectorX) < abs(movingVectorY)) {
                rowUsing = ROW_BOTTOM_TO_TOP
            } else {
                rowUsing = ROW_LEFT_TO_RIGHT
            }
        } else {
            if (movingVectorY > 0 && abs(movingVectorX) < abs(movingVectorY)) {
                rowUsing = ROW_TOP_TO_BOTTOM
            } else if (movingVectorY < 0 && abs(movingVectorX) < abs(movingVectorY)) {
                rowUsing = ROW_BOTTOM_TO_TOP
            } else {
                rowUsing = ROW_RIGHT_TO_LEFT
            }
        }
    }

    override fun draw(canvas: Canvas) {
        val bitmap = getCurrentMoveBitmap()
        canvas.drawBitmap(bitmap!!, x.toFloat(), y.toFloat(), null)
        // Last draw time.
        lastDrawNanoTime = System.nanoTime()
    }

    fun setMovingVector(movingVectorX: Int, movingVectorY: Int) {
        this.movingVectorX = movingVectorX
        this.movingVectorY = movingVectorY
    }
}