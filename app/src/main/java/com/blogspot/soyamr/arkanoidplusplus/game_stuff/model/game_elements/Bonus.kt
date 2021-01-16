package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.game_elements

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.IGameSurface

class Bonus(
    private val gameSurface: IGameSurface,
    image: Bitmap,
    x: Int,
    y: Int,
    val bonusType: BonusType
) :
    GameObject(
        image, 1, 1,
        x, y
    ) {
    private val _rect = Rect()
    public val rect:Rect
        get() {
            _rect.set(x, y, x + width, y + height)
            return _rect
        }
    var yVelocity = 600;
    override fun update(fps: Int) {
        y += (yVelocity / fps)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
    }
}