package com.blogspot.soyamr.arkanoidplusplus

class Dimensions(screenWidth: Int, screenHeight: Int) {

    private val _paddleYPosition = 100

    private val targetPixelsX = 1080
    private val targetPixelsY = 2260

    private val _ballWidth = 60;
    private val _ballHeight = 60;

    private val _paddleWidth = 280;
    private val _paddleHeight = 60;

    private val _polygonWidth = 120;
    private val _polygonHeight = 115;

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

    val polygonWidth = (_polygonWidth / scalingFactorX).toInt();
    val polygonHeight = (_polygonHeight / scalingFactorY).toInt();

    val padding = (_padding / scalingFactorX).toInt();

    val paddleInitialYPosition: Int = (_paddleYPosition / scalingFactorY).toInt();
}