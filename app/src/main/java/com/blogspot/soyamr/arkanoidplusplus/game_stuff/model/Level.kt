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
            for (column in 0 until model.dimensions.rectangleColMax) {
                for (row in 0..5) {

                    model.bricks[model.numBricks] =
                        Brick(
                            model,
                            column * model.dimensions.rectangleWidth + model.dimensions.padding * (column + 3),
                            row * model.dimensions.rectangleHeight + model.dimensions.padding * (row + 1),
                            BrickHardness.ONE, BrickType.RECTANGLE
                        )
                    if (row == 5) {
                        model.bricks[model.numBricks] =
                            Brick(
                                model,
                                column * model.dimensions.rectangleWidth + model.dimensions.padding * (column + 3),
                                row * model.dimensions.rectangleHeight + model.dimensions.padding * (row + 1),
                                BrickHardness.TWO, BrickType.RECTANGLE
                            )
                    }
                    if (row == 5 && (column == 0 || column == model.dimensions.rectangleColMax - 1)) {
                        model.bricks[model.numBricks] =
                            Brick(
                                model,
                                column * model.dimensions.rectangleWidth + model.dimensions.padding * (column + 3),
                                row * model.dimensions.rectangleHeight + model.dimensions.padding * (row + 1),
                                BrickHardness.THREE, BrickType.RECTANGLE
                            )
                        model.addBonusHere(model.numBricks, BonusType.PLUS_BALL)
                    }
                    ++model.numBricks
                }
            }
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
            // Outer loop to handle number of rows
            // n in this case
            // Outer loop to handle number of rows
            // n in this case
            var i = 0
            var j = 0
            while (i < 11) {

                // Inner loop to handle number of columns
                // values changing acc. to outer loop
                while (j <= i) {

                    if (j == i || i == 10 || j == 0) {
                        // Printing stars
                        model.bricks[model.numBricks] =
                            Brick(
                                model,
                                j * model.gameBitmaps.brickRectangleBlue.width +
                                        model.dimensions.padding * (j + 3),
                                i * model.gameBitmaps.brickRectangleBlue.height +
                                        model.dimensions.padding * (i + 1),
                                BrickHardness.THREE, BrickType.RECTANGLE
                            )
                        ++model.numBricks
                        j++
                    } else {
                        model.bricks[model.numBricks] =
                            Brick(
                                model,
                                j * model.gameBitmaps.brickRectangleBlue.width +
                                        model.dimensions.padding * (j + 3),
                                i * model.gameBitmaps.brickRectangleBlue.height +
                                        model.dimensions.padding * (i + 1),
                                BrickHardness.ONE, BrickType.RECTANGLE
                            )
                        ++model.numBricks
                        j++
                    }
                }
                j = 0 // we have to reset j value so as it can
                // start from begining and print * equal to i.
                i++
            }
            //add random bonus
            val rand = Random()
            var rn: Int
            for (i in 0..3) {
                rn = rand.nextInt(model.numBricks)
                model.addBonusHere(rn, BonusType.BIGGER_PADDLE)
            }
            for (i in 0..0) {
                rn = rand.nextInt(model.numBricks)
                model.addBonusHere(rn, BonusType.SMALLER_PADDLE)
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
            var size = 11
            for (row in 0 until size) {
                for (col in 0 until size) {
                    if (row == 5 && col == 5) {
                        model.bricks[model.numBricks] =
                            Brick(
                                model,
                                col * model.dimensions.rectangleWidth +
                                        model.dimensions.padding * (col + 3),
                                row * model.dimensions.rectangleHeight +
                                        model.dimensions.padding * (row + 1),
                                BrickHardness.DIAMOND,
                                BrickType.RECTANGLE
                            )
                        ++model.numBricks
                        ++model.unBreakableBricks

                    } else if (row == col || row + col == size - 1) {
                        model.bricks[model.numBricks] =
                            Brick(
                                model,
                                col * model.dimensions.rectangleWidth +
                                        model.dimensions.padding * (col + 3),
                                row * model.dimensions.rectangleHeight +
                                        model.dimensions.padding * (row + 1),
                                BrickHardness.ONE,
                                BrickType.RECTANGLE

                            )
                        ++model.numBricks
                    }
                }
//                when (row) {
//                    0, 1, 3, 7 ,8 -> model.addBonusHere(row, BonusType.SMALLER_PADDLE)
//                    2, 3,0,1 -> model.addBonusHere(row, BonusType.BIGGER_PADDLE)
//                    4, 5 -> model.addBonusHere(row, BonusType.BULLETS)
//                    4, 56, 7 -> model.addBonusHere(row, BonusType.PLUS_BALL)
//                    8, 9 -> model.addBonusHere(row, BonusType.PLUS_LIVE)
//                }
            }
            val rand = Random()
            var rn: Int
            for (i in 0..2) {
                rn = rand.nextInt(model.numBricks)
                model.addBonusHere(rn, BonusType.SMALLER_PADDLE)
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