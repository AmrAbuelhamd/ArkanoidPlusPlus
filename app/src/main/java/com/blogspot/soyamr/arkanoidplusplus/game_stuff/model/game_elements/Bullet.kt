package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.game_elements

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.IGameSurface

class Bullet(
    private val gameSurface: IGameSurface,
    private val images: Array<Bitmap>,
    x: Int,
    y: Int
) :
    GameObject(
        images[0]/*fixme*/, 1, 1,
        x, y
    ) {
    private val _rect = Rect()
    private val bulletRect: Rect
        get() {
            if(ctr==-1)
                ++ctr
            _rect.set(x, y, x + images[ctr].width, y + images[ctr].height)
            return _rect
        }
    var yVelocity = -1200;
    var ctr = -1;
    override fun update(fps: Int) {
        if (++ctr >= images.size)
            ctr = 0
        y += (yVelocity / fps)
    }

    override fun draw(canvas: Canvas) {
        if(ctr==-1)
            ++ctr
        canvas.drawBitmap(images[ctr], x.toFloat(), y.toFloat(), null)
    }

    fun intersects(rect: Rect): Boolean {
        return Rect.intersects(bulletRect, rect)
    }
}