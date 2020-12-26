package com.blogspot.soyamr.arkanoidplusplus

import android.graphics.Canvas
import android.view.SurfaceHolder

interface Controller {
    fun update()
    fun draw(canvas: Canvas)
    fun getHolder(): SurfaceHolder
    fun invalidate()
}