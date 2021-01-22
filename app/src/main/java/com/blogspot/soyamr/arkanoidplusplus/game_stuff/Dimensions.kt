package com.blogspot.soyamr.arkanoidplusplus.game_stuff

class Dimensions(screenWidth: Int, screenHeight: Int) {


    private val _paddleYPosition = 150

    private val _starHeight = 20
    private val _starWidth = 20

    private val _bulletHeight = 75
    private val _bulletWidth = 75

    private val targetPixelsX = 1080
    private val targetPixelsY = 2260

    private val _ballWidth = 30;
    private val _ballHeight = 30;

    private val _paddleWidth = 500//140;
    private val _paddleHeight = 30;

    private val _paddleWidthBig = 280;
    private val _paddleHeightBig = 30;

    private val _paddleWidthSmall = 70;
    private val _paddleHeightSmall = 30;

    private val _lifePaddleWidth = 56;
    private val _lifePaddleHeight = 12;

    //bricks
    private val _polygonWidth = 48;
    private val _polygonHeight = 42;
     val polygonColMax = 20

    private val _squareWidth = 43;
    private val _squareHeight = 43;
     val squareColMax = 22

    private val _rectangleWidth = 91;
    private val _rectangleHeight = 45;
    val rectangleColMax = 11

    private val _padding = 5;

    private val scalingFactorX: Double
    private val scalingFactorY: Double

    val screenHeight: Int
    val screenWidth: Int

    init {
        //target pixels on x axis / real pixels on x axis
        scalingFactorX = targetPixelsX / screenWidth.toDouble()
        scalingFactorY = targetPixelsY / screenHeight.toDouble()

        this.screenWidth = (targetPixelsX / scalingFactorX).toInt()
        this.screenHeight = (targetPixelsY / scalingFactorY).toInt()
    }

    val ballWidth = (_ballWidth / scalingFactorX).toInt()
    val ballHeight = (_ballHeight / scalingFactorY).toInt();

    val paddleWidth = (_paddleWidth / scalingFactorX).toInt();
    val paddleHeight = (_paddleHeight / scalingFactorY).toInt()

    val paddleWidthSmall = (_paddleWidthSmall / scalingFactorX).toInt();
    val paddleHeightSmall = (_paddleHeightSmall / scalingFactorY).toInt()

    val paddleWidthBig = (_paddleWidthBig / scalingFactorX).toInt();
    val paddleHeightBig = (_paddleHeightBig / scalingFactorY).toInt()

    val lifePaddleWidth = (_lifePaddleWidth / scalingFactorX).toInt();
    val lifePaddleHeight = (_lifePaddleHeight / scalingFactorY).toInt()

    //bricks

    val rectangleWidth = (_rectangleWidth / scalingFactorX).toInt();
    val rectangleHeight = (_rectangleHeight / scalingFactorY).toInt();

    val polygonWidth = (_polygonWidth / scalingFactorX).toInt();
    val polygonHeight = (_polygonHeight / scalingFactorY).toInt();

    val squareWidth = (_squareWidth / scalingFactorX).toInt();
    val squareHeight = (_squareHeight / scalingFactorY).toInt();

    val padding = (_padding / scalingFactorX).toInt();

    val paddleInitialYPosition: Int = (_paddleYPosition / scalingFactorY).toInt();

    val shieldYPosition: Int = screenHeight - paddleInitialYPosition + paddleHeight + padding

    val starHeight: Int = (_starHeight / scalingFactorY).toInt()
    val starWidth: Int = (_starWidth / scalingFactorX).toInt()

    val bulletHeight: Int = (_bulletHeight / scalingFactorY).toInt()
    val bulletWidth: Int = (_bulletWidth / scalingFactorX).toInt()
}