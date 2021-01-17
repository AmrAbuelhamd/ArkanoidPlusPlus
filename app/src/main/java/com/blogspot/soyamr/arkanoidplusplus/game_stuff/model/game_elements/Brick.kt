package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.game_elements

import android.graphics.Canvas
import android.graphics.Rect
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.BrickHardness
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.BrickType
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.Model

class Brick(
    private val model: Model,
    x: Int,
    y: Int,
    var brickHardness: BrickHardness,
    private val brickType: BrickType
) :
    GameObject(
        brickType.getBrick(brickHardness, model), 1, 1,
        x, y
    ) {

    public val rect = Rect()

    var brickColoredImage = image

    init {
        rect.set(
            x, y,
            x + image.width,
            y + image.height
        )
    }

    override fun update(fps: Int) {
//        TODO("Not yet implemented")
    }

    override fun draw(canvas: Canvas) {
        canvas.drawBitmap(brickColoredImage, x.toFloat(), y.toFloat(), null)
    }

    fun reduceLife():Boolean {
        //unbreakable brick
        if (brickHardness == BrickHardness.DIAMOND) {
            return false
        }
        brickHardness = brickHardness.next()
        if (brickHardness == BrickHardness.DEAD) {
            isVisible = false
            return true
        }
        brickColoredImage = brickType.getBrick(brickHardness, model)
        return false
    }

    fun getVisibility(): Boolean {
        return isVisible
    }

}