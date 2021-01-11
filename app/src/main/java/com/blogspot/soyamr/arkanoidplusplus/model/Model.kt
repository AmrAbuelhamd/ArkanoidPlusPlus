package com.blogspot.soyamr.arkanoidplusplus.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.media.AudioManager
import android.media.SoundPool
import android.util.Log
import com.blogspot.soyamr.arkanoidplusplus.Dimensions
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.IGameSurface
import com.blogspot.soyamr.arkanoidplusplus.model.game_elements.Ball
import com.blogspot.soyamr.arkanoidplusplus.model.game_elements.Brick
import com.blogspot.soyamr.arkanoidplusplus.model.game_elements.Paddle
import com.blogspot.soyamr.arkanoidplusplus.model.game_elements.State
import java.io.IOException


class Model(context: Context, val gameSurface: IGameSurface) : IModel, ModelBallInterface {
    val balls: List<Ball>
    val paddle: Paddle
    val bricks = arrayOfNulls<Brick>(200)
    var numBricks = 0

    // For sound FX
    var soundPool: SoundPool? = null
    var beep1ID = -1
    var beep2ID = -1
    var beep3ID = -1
    var loseLifeID = -1
    var explodeID = -1

    // The score
    var score = 0

    var lives = 3

    val brickWidth: Int = gameSurface.getScreenWidth() / 8
    val brickHeight: Int = gameSurface.getScreenHeight() / 10

    var brick: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.element_purple_polygon_glossy)

    private val dimensions: Dimensions =
        Dimensions(gameSurface.getScreenWidth(), gameSurface.getScreenHeight())

    init {

        brick = Bitmap.createScaledBitmap(
            brick,
            dimensions.polygonWidth,
            dimensions.polygonHeight,
            false
        )

        var ball = BitmapFactory.decodeResource(context.resources, R.drawable.ball_blue)
        ball = Bitmap.createScaledBitmap(ball, dimensions.ballWidth, dimensions.ballHeight, false)
        var paddleImg = BitmapFactory.decodeResource(context.resources, R.drawable.paddle_blu)
        paddleImg = Bitmap.createScaledBitmap(
            paddleImg,
            dimensions.paddleWidth,
            dimensions.paddleHeight,
            false
        )
        balls = listOf(
            Ball(
                this,
                gameSurface,
                ball,
                dimensions.screenWidth / 2,
                dimensions.screenHeight - dimensions.paddleInitialYPosition - dimensions.ballHeight
            )
        )
        paddle = Paddle(
            gameSurface,
            paddleImg,
            dimensions.screenWidth / 4,
            dimensions.screenHeight - dimensions.paddleInitialYPosition
        )

        // Build a wall of bricks


        soundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)

        try {
            // Load our fx in memory ready for use
            beep1ID = soundPool!!.load(context, R.raw.beep1, 0)
            beep2ID = soundPool!!.load(context, R.raw.beep2, 0)
            beep3ID = soundPool!!.load(context, R.raw.beep3, 0)
            loseLifeID = soundPool!!.load(context, R.raw.lose_life, 0)
            explodeID = soundPool!!.load(context, R.raw.explode, 0)
        } catch (e: IOException) {
            // Print an error message to the console
            Log.e("error", "failed to load sound files")
        }
        createBricksAndRestart()
    }

    private fun createBricksAndRestart() {
        balls.forEach { it.reset() }
        for (column in 0..7) {
            for (row in 0..2) {
                bricks[numBricks] =
                    Brick(
                        gameSurface,
                        brick,
                        column * dimensions.polygonWidth + dimensions.padding * (column + 3),
                        row * dimensions.polygonHeight + dimensions.padding * (row + 1)
                    )
                ++numBricks
            }
        }

        // Reset scores and lives
        score = 0;
        lives = 3;
    }


    override fun setMovementState(state: State) {
        paddle.paddleState = state
    }

    override fun update(fps: Int) {
        balls.forEach { it.update(fps) }
        paddle.update(fps)

        // Check for ball colliding with paddle
        balls.forEach {
            if (it.intersects(paddle.getRect())) {
                it.setRandomXVelocity();
                it.reverseYVelocity();
                it.clearObstacleY(paddle.getRect().top);
                soundPool!!.play(beep1ID, 1F, 1F, 0, 0, 1F);
            }
        }

        // Check for ball colliding with a brick
        for (i in 0 until numBricks) {
            if (bricks[i]!!.getVisibility()) {
                balls.forEach {
                    if (it.intersects(bricks[i]!!.rect)) {
                        bricks[i]!!.setInvisible()
                        it.reverseYVelocity()
                        score += 10
                        soundPool!!.play(explodeID, 1f, 1f, 0, 0, 1f)
                    }
                }
            }
        }
    }

    val paint = Paint()

    override fun draw(canvas: Canvas) {
        balls.forEach { it.draw(canvas) }
        paddle.draw(canvas)
        // Draw the bricks if visible
        for (i in 0 until numBricks) {
            if (bricks[i]!!.getVisibility()) {
                bricks[i]?.draw(canvas)
            }
        }

        // Draw the score
        paint.textSize = 40F;
        canvas.drawText("Score: $score   Lives: $lives", 10F, 50F, paint);

        // Has the player cleared the screen?
//        if (score == numBricks * 10) {
//            paint.textSize = 90F;
//            canvas.drawText(
//                "YOU HAVE WON!",
//                10F,
//                gameSurface.getScreenHeight().toFloat() / 2F,
//                paint
//            )
//            pause()
//            startScoreScreen(score)
//        }

        // Has the player lost?
//        if (lives <= 0) {
//            paint.textSize = 90F;
//            canvas.drawText(
//                "YOU HAVE LOST!",
//                10F,
//                gameSurface.getScreenHeight().toFloat() / 2, paint
//            )
//            pause()
//            startScoreScreen(score)
//        }

        if (score == numBricks * 10 || lives <= 0) {
            pause()
            startScoreScreen(score)
        }


    }

    override fun reduceLive() {
        --lives
    }

    override fun pause() {
        gameSurface.setPaused(true)
    }

    override fun playSoundLeft() {
        soundPool!!.play(beep3ID, 1F, 1F, 0, 0, 1F);
    }

    override fun playSoundRight() {
        soundPool!!.play(beep3ID, 1F, 1F, 0, 0, 1F);
    }

    override fun playSoundTop() {
        soundPool!!.play(beep2ID, 1F, 1F, 0, 0, 1F);
    }

    override fun playSoundBottom() {
        soundPool!!.play(loseLifeID, 1F, 1F, 0, 0, 1F);
    }

    private fun startScoreScreen(score: Int) {
        gameSurface.startScoreActivity(score)
    }
}