package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model

import android.graphics.Canvas
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.game_elements.BonusType
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.game_elements.Brick
import java.util.*

enum class Level : ILevel {
    FIRST {
        override val levelNum: Int = 1

        override fun initiateLevel(model: Model) {
            model.resetEverything()
            //create bricks
            for (column in 0 until model.dimensions.polygonColMax) {
                for (row in 0..3) {
                    model.bricks[model.numBricks] =
                        Brick(
                            model,
                            column * model.dimensions.polygonWidth + model.dimensions.padding * (column + 3),
                            row * model.dimensions.polygonHeight + model.dimensions.padding * (row + 1),
                            BrickHardness.FIVE, BrickType.POLYGON
                        )
                    /* if (row == 4)
                         model.addBonusHere(model.numBricks, BonusType.BULLETS)
                     else if(row == 1)
                         model.addBonusHere(model.numBricks, BonusType.PLUS_LIVE)
                     else if(row == 2)
                         model.addBonusHere(model.numBricks, BonusType.SMALLER_PADDLE)
                     else if(row == 3)
                         model.addBonusHere(model.numBricks, BonusType.SMALLER_PADDLE)
                     else if(row == 0)
                         model.addBonusHere(model.numBricks, BonusType.PLUS_BALL)*/

                    model.addBonusHere(model.numBricks, BonusType.PLUS_BALL)
                    ++model.numBricks
                }
            }
            //add random bonus
//            val rand = Random()
//            var rn = rand.nextInt(model.numBricks)
//            model.addBonusHere(rn, BonusType.PLUS_LIVE)
//
//            rn = rand.nextInt(model.numBricks)
//            model.addBonusHere(rn, BonusType.SMALLER_PADDLE)
//
//            rn = rand.nextInt(model.numBricks)
//            model.addBonusHere(rn, BonusType.SMALLER_PADDLE)
//
//            rn = rand.nextInt(model.numBricks)
//            model.addBonusHere(rn, BonusType.BIGGER_PADDLE)
//
//            rn = rand.nextInt(model.numBricks)
//            model.addBonusHere(rn, BonusType.BIGGER_PADDLE)
        }

        override fun setNextLevel(model: Model) {
            model.currentLevel = SECOND
        }
    },
    SECOND {
        override val levelNum: Int = 2

        override fun initiateLevel(model: Model) {
            model.resetEverything()
            //create bricks
            for (column in 0 until model.dimensions.squareColMax) {
                for (row in 0..3) {
                    model.bricks[model.numBricks] =
                        Brick(
                            model,
                            column * model.gameBitmaps.brickSquareBlue.width +
                                    model.dimensions.padding * (column + 3),
                            row * model.gameBitmaps.brickSquareBlue.height +
                                    model.dimensions.padding * (row + 1),
                            BrickHardness.DIAMOND, BrickType.SQUARE

                        )
                    model.addBonusHere(model.numBricks, BonusType.PLUS_BALL)
                    ++model.numBricks
                }
            }
            //add random bonus
            val rand = Random()
            var rn: Int
            for (i in 0..20) {
                rn = rand.nextInt(model.numBricks)
                model.addBonusHere(rn, BonusType.PLUS_LIVE)
            }
        }

        override fun setNextLevel(model: Model) {
            model.currentLevel = THIRD
        }
    },
    THIRD {
        override val levelNum: Int = 3

        override fun initiateLevel(model: Model) {
            model.resetEverything()
            //create bricks
            for (column in 0 until model.dimensions.rectangleColMax) {
                for (row in 0..3) {
                    model.bricks[model.numBricks] =
                        Brick(
                            model,
                            column * model.gameBitmaps.brickRectangleBlue.width +
                                    model.dimensions.padding * (column + 3),
                            row * model.gameBitmaps.brickRectangleBlue.height +
                                    model.dimensions.padding * (row + 1),
                            BrickHardness.ONE,
                            BrickType.RECTANGLE

                        )
                    model.addBonusHere(model.numBricks, BonusType.PLUS_BALL)
                    ++model.numBricks
                }
            }
        }

        override fun setNextLevel(model: Model) {
            model.currentLevel = FOURTH
        }
    },
    FOURTH {
        override val levelNum: Int = 4

        override fun initiateLevel(model: Model) {
            TODO("Not yet implemented")
        }

        override fun setNextLevel(model: Model) {
            model.currentLevel = FIFTH
        }
    },
    FIFTH {
        override val levelNum: Int = 5

        override fun initiateLevel(model: Model) {
            TODO("Not yet implemented")
        }

        override fun setNextLevel(model: Model) {
            model.currentLevel = BONUS
        }
    },
    BONUS {
        override val levelNum: Int = 6

        override fun initiateLevel(model: Model) {
            TODO("Not yet implemented")
        }

        override fun setNextLevel(model: Model) {
            throw RuntimeException()
        }

        override fun showWinButtons(model: Model, canvas: Canvas) {
            canvas.drawText(
                model.prizeText,
                model.prizeButtonX.toFloat(),
                model.prizeButtonY.toFloat(),
                model.paint
            )
        }
    };
}