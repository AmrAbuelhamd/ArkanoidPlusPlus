package com.blogspot.soyamr.arkanoidplusplus.model

import android.graphics.Canvas

interface IModel {
    fun update()
    fun draw(canvas: Canvas)
}