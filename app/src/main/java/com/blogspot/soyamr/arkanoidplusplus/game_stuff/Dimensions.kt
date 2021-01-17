package com.blogspot.soyamr.arkanoidplusplus.game_stuff

class Dimensions(screenWidth: Int, screenHeight: Int) {


    private val _paddleYPosition = 150

    private val _starHeight = 20
    private val _starWidth = 20

    private val _bulletHeight = 150
    private val _bulletWidth = 150

    private val targetPixelsX = 1080
    private val targetPixelsY = 2260

    private val _ballWidth = 60;
    private val _ballHeight = 60;

    private val _paddleWidth = 280;
    private val _paddleHeight = 60;

    private val _lifePaddleWidth = 56;
    private val _lifePaddleHeight = 12;

    private val _polygonWidth = 120;
    private val _polygonHeight = 115;
    private val polygonColMax = 8

    private val _squareWidth = 96;
    private val _squareHeight = 96;

    private val _padding = 10;

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

    val lifePaddleWidth = (_lifePaddleWidth / scalingFactorX).toInt();
    val lifePaddleHeight = (_lifePaddleHeight / scalingFactorY).toInt()

    val polygonWidth = (_polygonWidth / scalingFactorX).toInt();
    val polygonHeight = (_polygonHeight / scalingFactorY).toInt();

    val padding = (_padding / scalingFactorX).toInt();

    val paddleInitialYPosition: Int = (_paddleYPosition / scalingFactorY).toInt();

    val shieldYPosition: Int = screenHeight - paddleInitialYPosition + paddleHeight + padding

    val starHeight: Int = (_starHeight / scalingFactorY).toInt()
    val starWidth: Int = (_starWidth / scalingFactorX).toInt()

    val bulletHeight: Int = (_bulletHeight / scalingFactorY).toInt()
    val bulletWidth: Int = (_bulletWidth / scalingFactorX).toInt()
}