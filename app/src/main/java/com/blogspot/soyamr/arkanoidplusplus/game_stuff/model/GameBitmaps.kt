package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.Dimensions

class GameBitmaps(private val context: Context, private val dimensions: Dimensions) {

    var paddleImg: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.paddle_blu)

    var ball: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ball_blue)

    var brick: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.element_purple_polygon_glossy)

    var squareBrick: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.element_blue_square_glossy)

    var star: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.particle_star)
    var star2: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.particle_small_star)
    var star3: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.particle_cartoon_star)
    var bonusImg: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.element_green_square)

    var paddleImgLife: Bitmap
    init {
        brick = Bitmap.createScaledBitmap(
            brick,
            dimensions.polygonWidth,
            dimensions.polygonHeight,
            false
        )

        star = Bitmap.createScaledBitmap(
            star,
            dimensions.starWidth,
            dimensions.starHeight,
            false
        )
        star2 = Bitmap.createScaledBitmap(
            star2,
            dimensions.starWidth,
            dimensions.starHeight,
            false
        )
        star3 = Bitmap.createScaledBitmap(
            star3,
            dimensions.starWidth,
            dimensions.starHeight,
            false
        )

        ball = Bitmap.createScaledBitmap(ball, dimensions.ballWidth, dimensions.ballHeight, false)

        paddleImg = Bitmap.createScaledBitmap(
            paddleImg,
            dimensions.paddleWidth,
            dimensions.paddleHeight,
            false
        )

        bonusImg = Bitmap.createScaledBitmap(
            bonusImg,
            dimensions.ballWidth,
            dimensions.ballHeight,
            false
        )

        paddleImgLife = Bitmap.createScaledBitmap(
            paddleImg,
            dimensions.lifePaddleWidth,
            dimensions.lifePaddleHeight,
            false
        )
    }
}