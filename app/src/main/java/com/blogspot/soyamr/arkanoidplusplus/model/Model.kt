package com.blogspot.soyamr.arkanoidplusplus.model

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.blogspot.soyamr.arkanoidplusplus.IGameSurface
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.model.game_elements.Ball


class Model(context: Context, gameSurface: IGameSurface) : IModel {
    val balls: List<Ball>

    init {
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ball)
        val bitmap2 = BitmapFactory.decodeResource(context.resources, R.drawable.ball2)
        balls = listOf(
            Ball(gameSurface, bitmap2, 1200, 1200)
        )
    }

    override fun update() {
        balls.forEach(Ball::update)
    }


    override fun draw(canvas: Canvas) {
        balls.forEach { it.draw(canvas) }
    }
}