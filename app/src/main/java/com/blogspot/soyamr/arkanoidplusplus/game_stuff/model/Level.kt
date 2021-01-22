package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model

import android.graphics.Canvas
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.game_elements.BonusType
import java.util.*


enum class Level : ILevel {
    FIRST {
        override val levelNum: Int = 1

        override fun initiateLevel(model: Model) {
            model.resetEverything()
            //create bricks
            for (col in 0 until model.dimensions.rectangleColMax) {
                for (row in 0..5) {
                    if (row == 5 && (col == 0 || col == model.dimensions.rectangleColMax - 1)) {
                        model.addBrick(
                            col,
                            row,
                            model.dimensions.rectangleWidth,
                            model.dimensions.rectangleHeight,
                            BrickHardness.THREE,
                            BrickType.RECTANGLE
                        );
                        model.addBonusHere(model.numBricks - 1, BonusType.PLUS_BALL)
                    } else if (row == 5) {
                        model.addBrick(
                            col, row, model.dimensions.rectangleWidth,
                            model.dimensions.rectangleHeight, BrickHardness.TWO, BrickType.RECTANGLE
                        );
                    } else {
                        model.addBrick(
                            col,
                            row,
                            model.dimensions.rectangleWidth,
                            model.dimensions.rectangleHeight,
                            BrickHardness.ONE,
                            BrickType.RECTANGLE
                        );
                    }


                }
            }
            println("Bricksss ${model.numBricks}")
        }

        override fun setNextLevel(model: Model) {
            model.currentLevel = SECOND
        }
    },
    SECOND {
        override val levelNum: Int = 2

        override fun initiateLevel(model: Model) {
            model.resetEverything()
            var i = 0
            var j = 0
            while (i < 11) {
                while (j <= i) {
                    if (j == i || i == 10 || j == 0) {
                        model.addBrick(
                            j, i, model.dimensions.rectangleWidth, model.dimensions.rectangleHeight,
                            BrickHardness.THREE, BrickType.RECTANGLE
                        );
                        j++
                    } else {
                        model.addBrick(
                            j, i, model.dimensions.rectangleWidth, model.dimensions.rectangleHeight,
                            BrickHardness.ONE, BrickType.RECTANGLE
                        );
                        j++
                    }
                }
                j = 0
                i++
            }
            //add random bonus
            val rand = Random()
            var rn: Int
            for (ctr in 0..3) {
                rn = rand.nextInt(model.numBricks)
                model.addBonusHere(rn, BonusType.BIGGER_PADDLE)
            }
            for (ctr in 0..0) {
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
            val size = 21
            for (k in 0..1)
                for (row in 0 until size) {
                    for (col in 0 until size) {
                        if ((row == 4 && col == 10 || (row == 15 && col == 10)) && k == 0) {
                            model.addBrick(
                                col,
                                row,
                                model.dimensions.squareWidth,
                                model.dimensions.squareHeight,
                                BrickHardness.THREE,
                                BrickType.RECTANGLE
                            );
                        }
                        if (row == 10 && col == 10) {
                            model.addBrick(
                                col + k,
                                row,
                                model.dimensions.squareWidth,
                                model.dimensions.squareHeight,
                                BrickHardness.DIAMOND,
                                BrickType.SQUARE
                            );
                            ++model.unBreakableBricks

                        } else if (row == col || row + col == size - 1) {
                            model.addBrick(
                                col + k,
                                row,
                                model.dimensions.squareWidth,
                                model.dimensions.squareHeight,
                                BrickHardness.ONE,
                                BrickType.SQUARE
                            );
                        }
                    }
                }
            model.addBrick(
                8, 2, model.dimensions.squareWidth, model.dimensions.squareHeight,
                BrickHardness.TWO, BrickType.SQUARE
            );
            model.addBrick(
                9, 3, model.dimensions.squareWidth, model.dimensions.squareHeight,
                BrickHardness.TWO, BrickType.SQUARE
            );
            model.addBrick(
                13, 2, model.dimensions.squareWidth, model.dimensions.squareHeight,
                BrickHardness.TWO, BrickType.SQUARE
            );
            model.addBrick(
                12, 3, model.dimensions.squareWidth, model.dimensions.squareHeight,
                BrickHardness.TWO, BrickType.SQUARE
            );

            model.addBrick(
                8, 16, model.dimensions.squareWidth, model.dimensions.squareHeight,
                BrickHardness.TWO, BrickType.SQUARE
            );
            model.addBrick(
                9, 17, model.dimensions.squareWidth, model.dimensions.squareHeight,
                BrickHardness.TWO, BrickType.SQUARE
            );
            model.addBrick(
                13, 16, model.dimensions.squareWidth, model.dimensions.squareHeight,
                BrickHardness.TWO, BrickType.SQUARE
            );
            model.addBrick(
                12, 17, model.dimensions.squareWidth, model.dimensions.squareHeight,
                BrickHardness.TWO, BrickType.SQUARE
            );

//                when (row) {
//                    0, 1, 3, 7 ,8 -> model.addBonusHere(row, BonusType.SMALLER_PADDLE)
//                    2, 3,0,1 -> model.addBonusHere(row, BonusType.BIGGER_PADDLE)
//                    4, 5 -> model.addBonusHere(row, BonusType.BULLETS)
//                    4, 56, 7 -> model.addBonusHere(row, BonusType.PLUS_BALL)
//                    8, 9 -> model.addBonusHere(row, BonusType.PLUS_LIVE)
//                }

            val rand = Random()
            var rn: Int
            for (i in 0..2) {
                rn = rand.nextInt(model.numBricks)
                model.addBonusHere(rn, BonusType.SMALLER_PADDLE)
            }
            for (i in 0..2) {
                rn = rand.nextInt(model.numBricks)
                model.addBonusHere(rn, BonusType.BIGGER_PADDLE)
            }

            println(
                "numBricks " + model.numBricks + "  unBreakableBricks " + model.unBreakableBricks
            )
        }

        override fun setNextLevel(model: Model) {
            model.currentLevel = FOURTH
        }
    },
    FOURTH {
        override val levelNum: Int = 4

        override fun initiateLevel(model: Model) {
            model.resetEverything()
            //create bricks
            for (col in 0 until model.dimensions.squareColMax) {
                for (row in 0..10 step 2) {
                    if (row == 5 && (col == 0 || col == model.dimensions.squareColMax - 1)) {
                        model.addBrick(
                            col,
                            row,
                            model.dimensions.squareWidth,
                            model.dimensions.squareHeight,
                            BrickHardness.FIVE,
                            BrickType.SQUARE
                        );
                        model.addBonusHere(model.numBricks - 1, BonusType.PLUS_BALL)
                    } else if (row == 5) {
                        model.addBrick(
                            col, row, model.dimensions.squareWidth,
                            model.dimensions.squareHeight, BrickHardness.TWO, BrickType.SQUARE
                        );
                    } else {
                        model.addBrick(
                            col,
                            row,
                            model.dimensions.squareWidth,
                            model.dimensions.squareHeight,
                            BrickHardness.TWO,
                            BrickType.SQUARE
                        );
                    }
                }
            }
            for (col in 0 until model.dimensions.polygonColMax) {
                for (row in 1..11 step 2) {
                    if (row == 5 && (col == 0 || col == model.dimensions.polygonColMax - 1)) {
                        model.addBrick(
                            col,
                            row,
                            model.dimensions.polygonWidth,
                            model.dimensions.squareHeight,
                            BrickHardness.THREE,
                            BrickType.POLYGON
                        );
                        model.addBonusHere(model.numBricks - 1, BonusType.PLUS_BALL)
                    } else if (row == 5) {
                        model.addBrick(
                            col, row, model.dimensions.polygonWidth,
                            model.dimensions.squareHeight, BrickHardness.TWO, BrickType.POLYGON
                        );
                    } else {
                        model.addBrick(
                            col,
                            row,
                            model.dimensions.polygonWidth,
                            model.dimensions.squareHeight,
                            BrickHardness.ONE,
                            BrickType.POLYGON
                        );
                    }
                }
            }

            for (col in 1..9) {
                if (col == 5)
                    continue
                model.addBrick(
                    col,
                    12,
                    model.dimensions.rectangleWidth,
                    model.dimensions.rectangleHeight,
                    BrickHardness.DIAMOND,
                    BrickType.RECTANGLE
                );
                ++model.unBreakableBricks
            }
            println("Bricksss ${model.numBricks}")

        }

        override fun setNextLevel(model: Model) {
            model.currentLevel = FIFTH
        }
    },
    FIFTH {
        override val levelNum: Int = 5

        override fun initiateLevel(model: Model) {
            model.resetEverything()
            //create bricks
            for (col in 0 until model.dimensions.rectangleColMax) {
                for (row in 0..10 step 2) {
                    if (row == 5 && (col == 0 || col == model.dimensions.rectangleColMax - 1)) {
                        model.addBrick(
                            col,
                            row,
                            model.dimensions.rectangleWidth,
                            model.dimensions.rectangleHeight,
                            BrickHardness.THREE,
                            BrickType.RECTANGLE
                        );
                        model.addBonusHere(model.numBricks - 1, BonusType.PLUS_BALL)
                    } else if (row == 5) {
                        model.addBrick(
                            col, row, model.dimensions.rectangleWidth,
                            model.dimensions.rectangleHeight, BrickHardness.TWO, BrickType.RECTANGLE
                        );
                    } else {
                        model.addBrick(
                            col,
                            row,
                            model.dimensions.rectangleWidth,
                            model.dimensions.rectangleHeight,
                            BrickHardness.ONE,
                            BrickType.RECTANGLE
                        );
                        model.addBonusHere(model.numBricks - 1, BonusType.BULLETS)
                    }
                }
            }
            for (col in 0 until model.dimensions.polygonColMax) {
                for (row in 1..11 step 2) {
                    if (row == 5 && (col == 0 || col == model.dimensions.polygonColMax - 1)) {
                        model.addBrick(
                            col,
                            row,
                            model.dimensions.polygonWidth,
                            model.dimensions.rectangleHeight,
                            BrickHardness.THREE,
                            BrickType.POLYGON
                        );
                        model.addBonusHere(model.numBricks - 1, BonusType.PLUS_BALL)
                    } else if (row == 5) {
                        model.addBrick(
                            col, row, model.dimensions.polygonWidth,
                            model.dimensions.rectangleHeight, BrickHardness.TWO, BrickType.POLYGON
                        );
                    } else {
                        model.addBrick(
                            col,
                            row,
                            model.dimensions.polygonWidth,
                            model.dimensions.rectangleHeight,
                            BrickHardness.ONE,
                            BrickType.POLYGON
                        )
                        model.addBonusHere(model.numBricks - 1, BonusType.PLUS_BALL)
                    }
                }
            }
            println("Bricksss ${model.numBricks}")
        }

        override fun setNextLevel(model: Model) {
            model.currentLevel = BONUS
        }
    },
    BONUS {
        override val levelNum: Int = 6

        override fun initiateLevel(model: Model) {
            model.resetEverything()
            //create bricks
            val ctr = 3;
            //H
            for (col in 1 until 5) {
                if (col in 2..3) {
                    model.addBrick(
                        col,
                        ctr,
                        model.dimensions.polygonWidth,
                        model.dimensions.polygonHeight,
                        BrickHardness.FIVE,
                        BrickType.POLYGON
                    );
                    continue
                }
                for (row in 0..6) {
                    model.addBrick(
                        col,
                        row,
                        model.dimensions.polygonWidth,
                        model.dimensions.polygonHeight,
                        BrickHardness.FIVE,
                        BrickType.POLYGON
                    );
                }
            }
            //I
            for (row in 0..6 step 6) {
                model.addBrick(
                    6,
                    row,
                    model.dimensions.polygonWidth,
                    model.dimensions.polygonHeight,
                    BrickHardness.FIVE,
                    BrickType.RECTANGLE
                );
            }
            for (row in 1..5) {
                model.addBrick(
                    7,
                    row,
                    model.dimensions.squareWidth + 1,
                    model.dimensions.squareHeight,
                    BrickHardness.FIVE,
                    BrickType.SQUARE
                );
            }
            //T
//            for (col in 9..11) {
            model.addBrick(
                9,
                0,
                model.dimensions.polygonWidth - 2,
                model.dimensions.rectangleHeight,
                BrickHardness.FIVE,
                BrickType.RECTANGLE
            );
            model.addBrick(
                11,
                0,
                model.dimensions.polygonWidth - 2,
                model.dimensions.rectangleHeight,
                BrickHardness.FIVE,
                BrickType.RECTANGLE
            );
//            }
            for (row in 1..6) {
                model.addBrick(
                    10,
                    row,
                    model.dimensions.polygonWidth,
                    model.dimensions.polygonHeight,
                    BrickHardness.FIVE,
                    BrickType.POLYGON
                );
            }
            //S
            for (row in 0..7 step 3)
                for (col in 8..9) {
                    model.addBrick(
                        col,
                        row,
                        model.dimensions.rectangleWidth,
                        model.dimensions.rectangleHeight,
                        BrickHardness.FIVE,
                        BrickType.RECTANGLE
                    );
                }
            for (row in 1..2) {
                model.addBrick(
                    15,
                    row,
                    model.dimensions.squareWidth,
                    model.dimensions.rectangleHeight,
                    BrickHardness.FIVE,
                    BrickType.SQUARE
                );
            }
            for (row in 4..5) {
                model.addBrick(
                    20,
                    row,
                    model.dimensions.squareWidth,
                    model.dimensions.rectangleHeight,
                    BrickHardness.FIVE,
                    BrickType.SQUARE
                );
            }
            val rand = Random()
            var rn: Int
            for (i in 0..40) {
                rn = rand.nextInt(model.numBricks)
                model.addBonusHere(rn, BonusType.BULLETS)
            }
            println("Bricksss ${model.numBricks}")
        }

        override fun setNextLevel(model: Model) {
            throw RuntimeException("no next level button at all showed on screen")
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