package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model

import android.graphics.Bitmap

enum class BrickType {
    POLYGON {
        override fun getBrick(brickHardness: BrickHardness, model: Model): Bitmap {
            return when (brickHardness) {
                BrickHardness.ONE ->
                    model.gameBitmaps.brickPolygonBlue
                BrickHardness.TWO ->
                    model.gameBitmaps.brickPolygonGreen
                BrickHardness.THREE ->
                    model.gameBitmaps.brickPolygonYellow
                BrickHardness.FOUR ->
                    model.gameBitmaps.brickPolygonRed
                BrickHardness.FIVE ->
                    model.gameBitmaps.brickPolygonPurple
                BrickHardness.DIAMOND ->
                    model.gameBitmaps.brickPolygonGrey
                BrickHardness.DEAD -> throw RuntimeException("asking for dead brick bitmap")
            }
        }
    },
    SQUARE{
        override fun getBrick(brickHardness: BrickHardness, model: Model): Bitmap {
            return when (brickHardness) {
                BrickHardness.ONE ->
                    model.gameBitmaps.brickSquareBlue
                BrickHardness.TWO ->
                    model.gameBitmaps.brickSquareGreen
                BrickHardness.THREE ->
                    model.gameBitmaps.brickSquareYellow
                BrickHardness.FOUR ->
                    model.gameBitmaps.brickSquareRed
                BrickHardness.FIVE ->
                    model.gameBitmaps.brickSquarePurple
                BrickHardness.DIAMOND ->
                    model.gameBitmaps.brickSquareGrey
                BrickHardness.DEAD -> throw RuntimeException("asking for dead brick bitmap")
            }
        }
    },
    RECTANGLE {
        override fun getBrick(brickHardness: BrickHardness, model: Model): Bitmap {
            return when (brickHardness) {
                BrickHardness.ONE ->
                    model.gameBitmaps.brickRectangleBlue
                BrickHardness.TWO ->
                    model.gameBitmaps.brickRectangleGreen
                BrickHardness.THREE ->
                    model.gameBitmaps.brickRectangleYellow
                BrickHardness.FOUR ->
                    model.gameBitmaps.brickRectangleRed
                BrickHardness.FIVE ->
                    model.gameBitmaps.brickRectanglePurple
                BrickHardness.DIAMOND ->
                    model.gameBitmaps.brickRectangleGrey
                BrickHardness.DEAD -> throw RuntimeException("asking for dead brick bitmap")
            }
        }
    };

    abstract fun getBrick(brickHardness: BrickHardness, model: Model): Bitmap
}