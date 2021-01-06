package com.blogspot.soyamr.arkanoidplusplus.model.game_elements

import android.graphics.Bitmap
import android.graphics.Canvas
import com.blogspot.soyamr.arkanoidplusplus.model.IModel


abstract class GameObject(
    protected val image: Bitmap,
    protected var rowCount: Int,
    protected var colCount: Int,
    protected var x: Int,
    protected var y: Int
) : IModel {
    var isAlive = true
    protected val WIDTH: Int
    protected val HEIGHT: Int

    protected val width: Int
    protected val height: Int


    init {
        this.WIDTH = image.width
        this.HEIGHT = image.height
        this.width = this.WIDTH / colCount
        this.height = this.HEIGHT / rowCount
    }

    abstract override fun update();
    abstract override fun draw(canvas: Canvas)

    protected open fun createSubImageAt(row: Int, col: Int): Bitmap {
        // createBitmap(bitmap, x, y, width, height).
        return Bitmap.createBitmap(image, col * width, row * height, width, height)
    }
}