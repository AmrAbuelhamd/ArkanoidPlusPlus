package com.blogspot.soyamr.arkanoidplusplus.model

import android.graphics.Canvas

interface GlobalBehavior {

    fun update(fps: Int)
    fun draw(canvas: Canvas)
}