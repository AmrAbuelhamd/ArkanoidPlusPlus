package com.blogspot.soyamr.arkanoidplusplus.model.game_elements

import android.graphics.Bitmap
import android.graphics.Canvas
import com.blogspot.soyamr.arkanoidplusplus.model.IModel


abstract class GameObject : IModel {
    var isAlive = true
    protected var x = 0
    protected var y = 0
    protected val WIDTH: Int
    protected val HEIGHT: Int
    protected val image: Bitmap
    protected var rowCount = 0
    protected var colCount = 0

    protected val width: Int
    protected val height: Int


    constructor(image: Bitmap, rowCount: Int, colCount: Int, x: Int, y: Int) {
        this.image = image
        this.rowCount = rowCount
        this.colCount = colCount

        this.x = x
        this.y = y

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