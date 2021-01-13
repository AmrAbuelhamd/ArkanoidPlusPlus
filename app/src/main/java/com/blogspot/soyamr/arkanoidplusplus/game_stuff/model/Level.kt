package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model

import android.graphics.Canvas
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.game_elements.Brick
import java.util.*

public enum class Level : ILevel {
    FIRST {
        override val levelNum: Int = 1

        override fun initiateLevel(model: Model) {
            model.resetEverything()
            //create bricks
            for (column in 0..1) {
                for (row in 0..1) {
                    model.bricks[model.numBricks] =
                        Brick(
                            model.gameSurface,
                            model.gameBitmaps.brick,
                            column * model.dimensions.polygonWidth + model.dimensions.padding * (column + 3),
                            row * model.dimensions.polygonHeight + model.dimensions.padding * (row + 1)
                        )
                    ++model.numBricks
                }
            }
            //add random bonus
            val rn = Random().nextInt(model.numBricks)
            model.addBonusHere(rn)
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
            for (column in 0..9) {
                for (row in 0..3) {
                    model.bricks[model.numBricks] =
                        Brick(
                            model.gameSurface,
                            model.gameBitmaps.squareBrick,
                            column * model.gameBitmaps.squareBrick.width + model.dimensions.padding * (column + 3),
                            row * model.gameBitmaps.squareBrick.height + model.dimensions.padding * (row + 1)
                        )
                    ++model.numBricks
                }
            }
            //add random bonus
            val rand = Random()
            var rn: Int
            for (i in 0..20) {
                rn = rand.nextInt(model.numBricks)
                model.addBonusHere(rn)
            }
        }

        override fun setNextLevel(model: Model) {
            model.currentLevel = THIRD
        }
    },
    THIRD {
        override val levelNum: Int = 3

        override fun initiateLevel(model: Model) {
            TODO("Not yet implemented")
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
    },
}