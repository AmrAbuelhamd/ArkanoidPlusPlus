package com.blogspot.soyamr.arkanoidplusplus.game_stuff

import android.graphics.Canvas
import android.view.SurfaceHolder

interface Controller {
    fun update(fps:Int)
    fun drawScene(canvas: Canvas)
    fun getHolder(): SurfaceHolder
    fun invalidate()
}