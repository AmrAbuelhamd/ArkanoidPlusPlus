package com.blogspot.soyamr.arkanoidplusplus.model.game_elements

import android.graphics.Canvas

abstract class GameObject {
    abstract fun update();
    abstract fun draw(canvas: Canvas)
}