package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model

import android.content.Context
import android.graphics.*
import android.media.AudioManager
import android.media.SoundPool
import android.util.Log
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.Dimensions
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.IGameSurface
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.game_elements.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class Model(context: Context, private val gameSurface: IGameSurface) : IModel, ModelBallInterface {
    private val numberOfStars = 200
    private val balls: List<Ball>
    private val bonuses: Array<Bonus?> = arrayOfNulls<Bonus>(200)
    private val bonusesTracker: Array<Boolean?> = arrayOfNulls<Boolean>(200)
    private val bonusesIndexes: MutableList<Int> = ArrayList<Int>()
    private val screenElements: Array<ScreenElement?> = arrayOfNulls<ScreenElement>(numberOfStars)
    private val paddle: Paddle
    private val bricks = arrayOfNulls<Brick>(200)
    var numBricks = 0

    var livesNum = 3
    var lives: MutableList<ScreenElement> = ArrayList()

    // For sound FX
    var soundPool: SoundPool? = null
    var beep1ID = -1
    var beep2ID = -1
    var beep3ID = -1
    var loseLifeID = -1
    var explodeID = -1

    // The score
    var score = 0

    var paddleImg: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.paddle_blu)

    var ball: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ball_blue)

    var brick: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.element_purple_polygon_glossy)

    var star: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.particle_star)
    var star2: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.particle_small_star)
    var star3: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.particle_cartoon_star)

    var bonusImg =
        BitmapFactory.decodeResource(context.resources, R.drawable.element_green_square)

    private val dimensions: Dimensions =
        Dimensions(gameSurface.getScreenWidth(), gameSurface.getScreenHeight())

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
        createSpace()
        createLives()
        createBricksAndRestart()
    }
    private lateinit var paddleImgLife:Bitmap
    private fun createLives() {
        paddleImgLife = Bitmap.createScaledBitmap(
            paddleImg,
            dimensions.lifePaddleWidth,
            dimensions.lifePaddleHeight,
            false
        )
        lives = mutableListOf(
            ScreenElement(
                this,
                gameSurface,
                paddleImgLife,
                dimensions.lifePaddleWidth + dimensions.padding,
                gameSurface.getScreenHeight() - dimensions.padding * 5 - dimensions.lifePaddleHeight
            ),
            ScreenElement(
                this,
                gameSurface,
                paddleImgLife,
                dimensions.lifePaddleWidth * 2 + dimensions.padding * 2,
                gameSurface.getScreenHeight() - dimensions.padding * 5 - dimensions.lifePaddleHeight
            ),
            ScreenElement(
                this,
                gameSurface,
                paddleImgLife,
                dimensions.lifePaddleWidth * 3 + dimensions.padding * 3,
                gameSurface.getScreenHeight() - dimensions.padding * 5 - dimensions.lifePaddleHeight
            )
        )
    }

    private fun createSpace() {
        val random = Random()
        var ctr = 0;
        var currentStar = star
        for (row in 0 until numberOfStars) {
            val rn = random.nextInt(100)
            if (rn < 30) {
                currentStar = star2
            } else if (rn < 33) {
                currentStar = star3
            }
            screenElements[row] = ScreenElement(
                this,
                gameSurface,
                currentStar,
                random.nextInt(gameSurface.getScreenWidth()),
                random.nextInt(gameSurface.getScreenHeight())
            )
            currentStar = star
        }
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
        val rn = Random().nextInt(numBricks)
        bonusesIndexes.add(rn)
        bonuses[rn] = Bonus(
            gameSurface,
            bonusImg,
            bricks[rn]!!.rect.left,
            bricks[rn]!!.rect.top,
            BonusType.PLUS_LIVE
        )

        // Reset scores and lives
        score = 0;
        livesNum = 3
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
                it.adjustAngel(paddle.getRect())
                it.decideBallNewVelocityAccordingToPaddle(paddle.paddleState, paddle.getRect())
                it.clearObstacleY(paddle.getRect().top);
                soundPool!!.play(beep1ID, 1F, 1F, 0, 0, 1F);
            }
        }

        // Check for ball colliding with a brick
        var flag = false
        for (i in 0 until numBricks) {
            if (bricks[i]!!.getVisibility()) {
                balls.forEach {
                    if (it.intersects(bricks[i]!!.rect)) {
                        bricks[i]!!.setInvisible()
                        it.decideBallNewVelocityAccordingToBrick(bricks[i]!!.rect)
                        score += 10
                        soundPool!!.play(explodeID, 1f, 1f, 0, 0, 1f)
                        flag = true
                        if (bonusesIndexes.contains(i)) {
                            bonusesTracker[i] = true
                        }
                    }
                }
                if (flag)
                    break
            }
        }

        //update bonuses
        bonusesIndexes.forEach {
            if (bonusesTracker[it] == true) {
                bonuses[it]?.update(fps)
            }
        }

        //check for bonus colliding with paddle
        bonusesIndexes.forEach {
            if (bonusesTracker[it] == true) {
                if (Rect.intersects(bonuses[it]!!.rect, paddle.getRect())) {
                    bonusesTracker[it] = false
                    addTheBonus(bonuses[it]!!)
                }
            }
        }

    }

    private fun addTheBonus(bonus: Bonus) {
        when (bonus.bonusType) {
            BonusType.PLUS_LIVE -> {
                val count = lives.size
                lives.add(
                    ScreenElement(
                        this,
                        gameSurface,
                        paddleImgLife,
                        dimensions.lifePaddleWidth * (count + 1) + dimensions.padding * (count + 1),
                        gameSurface.getScreenHeight() - dimensions.padding * 5 - dimensions.lifePaddleHeight
                    )
                )
            }
        }
    }

    override fun draw(canvas: Canvas) {
        balls.forEach { it.draw(canvas) }
        screenElements.forEach { it?.draw(canvas) }
        lives.forEach { it?.draw(canvas) }
        paddle.draw(canvas)
        // Draw the bricks if visible
        for (i in 0 until numBricks) {
            if (bricks[i]!!.getVisibility()) {
                bricks[i]?.draw(canvas)
            }
        }

        //draw the bonus falling down
        bonusesIndexes.forEach {
            if (bonusesTracker[it] == true) {
                bonuses[it]?.draw(canvas)
            }
        }

//        // Draw the score
//        paint.textSize = 40F;
//        canvas.drawText("Score: $score   Lives: $livesNum", 10F, 50F, paint);

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

        if (score == numBricks * 10 || livesNum <= 0) {
            pause()
            startScoreScreen(score)
        }


    }

    override fun reduceLive() {
        --livesNum
        lives.removeLast()
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