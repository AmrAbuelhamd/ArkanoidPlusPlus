package com.blogspot.soyamr.arkanoidplusplus

import android.graphics.Canvas

interface IModel {
    fun update()
    fun draw(canvas: Canvas)
}