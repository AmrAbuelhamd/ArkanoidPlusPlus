package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.SystemClock
import androidx.core.content.res.ResourcesCompat
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.Dimensions
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.IGameSurface
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.game_elements.*
import java.util.*
import kotlin.collections.ArrayList


class Model(context: Context, val gameSurface: IGameSurface, var currentLevel: ILevel) :
    IModel, ModelBallInterface {
    private val numberOfStars = 200
    private val balls: MutableList<Ball> = ArrayList()
    private val bonuses: Array<Bonus?> = arrayOfNulls<Bonus>(200)
    private val bonusesTracker: Array<Boolean?> = arrayOfNulls<Boolean>(200)
    private val bonusesIndexes: MutableList<Int> = ArrayList<Int>()
    private val screenElements: Array<ScreenElement?> = arrayOfNulls<ScreenElement>(numberOfStars)
    private val paddle: Paddle
    val bricks = arrayOfNulls<Brick>(200)
    var numBricks = 0

    var lives: MutableList<ScreenElement> = ArrayList()

    // The score
    private var score = 0

    override val dimensions: Dimensions =
        Dimensions(gameSurface.getScreenWidth(), gameSurface.getScreenHeight())
    val gameBitmaps = GameBitmaps(context, dimensions)
    private val gameSounds = SoundManger(context)
    val paint = Paint()

    val nextLevelButtonX = dimensions.screenWidth / 3
    val nextLevelButtonY = dimensions.screenHeight / 3

    val mainMenuButtonX = dimensions.screenWidth / 3
    val mainMenuButtonY = dimensions.screenHeight / 3 + dimensions.padding * 20

    val prizeButtonX = dimensions.screenWidth / 3
    val prizeButtonY = dimensions.screenHeight / 3

    val nextLevelText = "NEXT LEVEL"
    val mainMenuText = "MAIN MENU"
    val prizeText = "PRIZE"

    var hasWon: Boolean? = null
    var mainMenuTextBounds = Rect()
    var nextLevelTextBounds = Rect()
    var prizeTextBounds = Rect()

    init {
        paddle = Paddle(
            this,
            gameBitmaps.paddleImg,
            dimensions.screenWidth / 4,
            dimensions.screenHeight - dimensions.paddleInitialYPosition
        )

        currentLevel.initiateLevel(this)

        val customTypeface = ResourcesCompat.getFont(context, R.font.aldrich)
        paint.typeface = customTypeface
        paint.textSize = 90F
        paint.color = Color.WHITE

        paint.getTextBounds(mainMenuText, 0, mainMenuText.length, mainMenuTextBounds);
        paint.getTextBounds(prizeText, 0, prizeText.length, prizeTextBounds);
        paint.getTextBounds(nextLevelText, 0, nextLevelText.length, nextLevelTextBounds);

    }

    var startTime: Long = 0L
    fun resetEverything() {
        bonusesIndexes.clear()

        for (el in bonusesTracker.withIndex())
            bonusesTracker[el.index] = false

        balls.clear()
        balls.add(
            Ball(
                this,
                gameSurface,
                gameBitmaps.ball,
                dimensions.screenWidth / 2,
                dimensions.screenHeight - dimensions.paddleInitialYPosition - dimensions.ballHeight
            )
        )

        startTime = SystemClock.uptimeMillis()
        hasWon = null
        numBricks = 0;
        score = 0;
        bonusesIndexes.clear()
        for (i in 0 until numBricks) {
            bonusesTracker[i] = false
        }
        createLives()
        createSpace()
        pause = true
        balls.forEach { it.reset() }
        paddle.reset()
    }

    private fun createLives() {
        lives = mutableListOf(
            ScreenElement(
                this,
                gameSurface,
                gameBitmaps.paddleImgLife,
                dimensions.lifePaddleWidth + dimensions.padding,
                gameSurface.getScreenHeight() - dimensions.padding * 5 - dimensions.lifePaddleHeight
            ),
            ScreenElement(
                this,
                gameSurface,
                gameBitmaps.paddleImgLife,
                dimensions.lifePaddleWidth * 2 + dimensions.padding * 2,
                gameSurface.getScreenHeight() - dimensions.padding * 5 - dimensions.lifePaddleHeight
            ),
            ScreenElement(
                this,
                gameSurface,
                gameBitmaps.paddleImgLife,
                dimensions.lifePaddleWidth * 3 + dimensions.padding * 3,
                gameSurface.getScreenHeight() - dimensions.padding * 5 - dimensions.lifePaddleHeight
            )
        )
    }

    private fun createSpace() {
        val random = Random()
        var ctr = 0;
        var currentStar = gameBitmaps.star
        for (row in 0 until numberOfStars) {
            val rn = random.nextInt(100)
            if (rn < 30) {
                currentStar = gameBitmaps.star2
            } else if (rn < 33) {
                currentStar = gameBitmaps.star3
            }
            screenElements[row] = ScreenElement(
                this,
                gameSurface,
                currentStar,
                random.nextInt(gameSurface.getScreenWidth()),
                random.nextInt(gameSurface.getScreenHeight())
            )
            currentStar = gameBitmaps.star
        }
    }

    private fun createBricksAndRestart() {
        //reset balls
        balls.forEach { it.reset() }
        for (column in 0..7) {
            for (row in 0..2) {
                bricks[numBricks] =
                    Brick(
                        gameSurface,
                        gameBitmaps.brick,
                        column * dimensions.polygonWidth + dimensions.padding * (column + 3),
                        row * dimensions.polygonHeight + dimensions.padding * (row + 1)
                    )
                ++numBricks
            }
        }
        //add random bonus
        val rn = Random().nextInt(numBricks)
        bonusesIndexes.add(rn)
        bonuses[rn] = Bonus(
            gameSurface,
            gameBitmaps.bonusImg,
            bricks[1]!!.rect.left,
            bricks[1]!!.rect.top,
            BonusType.PLUS_LIVE
        )
    }


    override fun setMovementState(state: State) {
        paddle.paddleState = state
    }

    var pause = true
    override fun update(fps: Int) {
        if (!pause) {
            balls.forEach { it.update(fps) }

            //check ball colliding with screen bottom
            val itr = balls.iterator()
            while (itr.hasNext()) {
                val ball = itr.next()
                if (ball.y > gameSurface.getScreenHeight() - ball.height) {
                    if (balls.size == 1) {
                        ball.reset()
                        pause()
                        reduceLive()
                        playSoundBottom()
                        break
                    }//else there is more balls, so just remove this ball
                    itr.remove()
                }
            }

            paddle.update(fps)

            // Check for ball colliding with paddle
            balls.forEach {
                if (it.intersects(paddle.getRect())) {
                    it.adjustAngel(paddle.getRect())
                    it.decideBallNewVelocityAccordingToPaddle(
                        paddle.paddleState,
                        paddle.getRect()
                    )
                    it.clearObstacleY(paddle.getRect().top);
                    gameSounds.ballCollideWithPaddle()
                }
            }

            balls.forEach {
                for (i in 0 until numBricks) {
                    if (bricks[i]!!.getVisibility()) {
                        if (it.intersects(bricks[i]!!.rect)) {
                            bricks[i]!!.setInvisible()
                            it.decideBallNewVelocityAccordingToBrick(bricks[i]!!.rect)
                            score += 10
                            gameSounds.ballCollideWithBrick()
                            if (bonusesIndexes.contains(i)) {
                                bonusesTracker[i] = true
                            }
                            break
                        }
                    }
                }
            }
//            // Check for ball colliding with a brick
//            var flag = false
//            for (i in 0 until numBricks) {
//                if (bricks[i]!!.getVisibility()) {
//                    balls.forEach {
//                        if (it.intersects(bricks[i]!!.rect)) {
//                            if (bricks[i]!!.getVisibility()) {
//                                bricks[i]!!.setInvisible()
//                                it.decideBallNewVelocityAccordingToBrick(bricks[i]!!.rect)
//                                score += 10
//                                gameSounds.ballCollideWithBrick()
//                                flag = true
//                                if (bonusesIndexes.contains(i)) {
//                                    bonusesTracker[i] = true
//                                }
//                            }
//                        }
//                    }
//                    if (flag) {
//                        break
//                    }
//                }
//            }

            //update bonuses
            bonusesIndexes.forEach {
                if (bonusesTracker[it] == true) {
                    bonuses[it]?.update(fps)
                    if (bonuses[it]!!.rect.bottom < 0)
                        bonusesTracker[it] = false

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
    }

    private fun addTheBonus(bonus: Bonus) {
        when (bonus.bonusType) {
            BonusType.PLUS_LIVE -> {
                val count = lives.size
                lives.add(
                    ScreenElement(
                        this,
                        gameSurface,
                        gameBitmaps.paddleImgLife,
                        dimensions.lifePaddleWidth * (count + 1) + dimensions.padding * (count + 1),
                        gameSurface.getScreenHeight() - dimensions.padding * 5 - dimensions.lifePaddleHeight
                    )
                )
            }
            BonusType.SMALLER_PADDLE -> {
                paddle.changePaddleImgState(paddle.imgSmall)
            }
            BonusType.BIGGER_PADDLE -> {
                paddle.changePaddleImgState(paddle.imgBig)
            }
            BonusType.PLUS_BALL -> {
                balls.add(
                    Ball(
                        this,
                        gameSurface,
                        gameBitmaps.ball,
                        dimensions.screenWidth / 2 - dimensions.ballWidth * 2,
                        dimensions.screenHeight / 2,
                    ).also { it.reverseXVelocity() }
                )
                balls.add(
                    Ball(
                        this,
                        gameSurface,
                        gameBitmaps.ball,
                        dimensions.screenWidth / 2 + dimensions.ballWidth * 2,
                        dimensions.screenHeight / 2,
                    )
                )
                balls.add(
                    Ball(
                        this,
                        gameSurface,
                        gameBitmaps.ball,
                        dimensions.screenWidth / 2,
                        dimensions.screenHeight / 2,
                    ).also {
                        it.reverseYVelocity()
                        it.xVelocity = 0
                    }
                )
            }
            BonusType.SHIELD -> TODO()
            BonusType.BULLETS -> TODO()
        }
    }

    override fun draw(canvas: Canvas) {
        balls.forEach { if (it.isAlive) it.draw(canvas) }
        screenElements.forEach { it?.draw(canvas) }
        lives.forEach { it.draw(canvas) }
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


        // Has the player cleared the screen? (WIN)
        if (score == numBricks * 10) {
            hasWon = true
            pause = true
            currentLevel.showWinButtons(this, canvas)
        }

        // Has the player lost? (LOST)
        if (lives.size <= 0) {
            hasWon = false
            pause = true
            currentLevel.showLoseButtons(this, canvas)
//            val rect = Rect()
//            rect.set(mainMenuButtonX,mainMenuButtonY,
//                (mainMenuButtonX + mainMenuTextBounds.width()),(mainMenuButtonY - mainMenuTextBounds.height()))
//            canvas.drawRect(rect,paint)
        }
//
//        if (score == numBricks * 10 || lives.size <= 0) {
//            pause = true;
//            paint.textSize = 90F;
//            paint.setColor(Color.RED)
//            canvas.drawText(
//                "YOU HAVE WON!",
//                10F,
//                gameSurface.getScreenHeight().toFloat() / 2F,
//                paint
//            )
//            //gameSounds.release()
//            //startScoreScreen(score)
//        }
    }

    override fun reduceLive() {
        lives.removeLast()
    }

    override fun playSoundLeft() {
        gameSounds.ballCollideWithLeftWall()
    }

    override fun playSoundRight() {
        gameSounds.ballCollideWithRightWall()
    }

    override fun playSoundTop() {
        gameSounds.ballCollideWithTopRoof()
    }

    override fun playSoundBottom() {
        gameSounds.ballCollideWithBottomGround()
    }

    override fun pause() {
        pause = true
    }

    fun showMainMenu() {
        gameSurface.showMainMenu()
    }

    private fun showPrize() {
        gameSurface.showMainMenu()//fixme
    }

    fun addBonusHere(rn: Int, bonus: BonusType) {
        if (rn > numBricks)
            return
        bonusesIndexes.add(rn)
        bonuses[rn] = Bonus(
            gameSurface,
            gameBitmaps.bonusImg,
            bricks[rn]!!.rect.left,
            bricks[rn]!!.rect.top,
            bonus
        )
    }

    fun getScreenWidth() =
        dimensions.screenWidth

    fun getScreenHeight() =
        dimensions.screenHeight

    override fun setPaused(paused: Boolean) {
        if (hasWon == null)
            this.pause = paused
    }

    override fun userTouched(x: Float, y: Float) {
        if (hasWon == true) {
            if (currentLevel == Level.BONUS) {
                if (touchedPrizeButton(x.toInt(), y.toInt())) {
                    gameSurface.hereIsUserScores(calcScore(), currentLevel.levelNum)
                    showPrize()
                }
                return
            }
            if (touchedMainMenuButton(x.toInt(), y.toInt())) {
                gameSurface.hereIsUserScores(calcScore(), currentLevel.levelNum)
                showMainMenu()
            } else if (touchedNextLevelButton(x.toInt(), y.toInt())) {
                gameSurface.hereIsUserScores(calcScore(), currentLevel.levelNum)
                currentLevel.setNextLevel(this)
                currentLevel.initiateLevel(this)
            }
        } else if (hasWon == false) {
            if (touchedMainMenuButton(x.toInt(), y.toInt())) {
                gameSurface.hereIsUserScores(-1, -1)
                showMainMenu()
            }
        }
    }

    private fun touchedNextLevelButton(x: Int, y: Int) =
        x in nextLevelButtonX..(nextLevelButtonX + nextLevelTextBounds.width()) &&
                y in (nextLevelButtonY - nextLevelTextBounds.height())..nextLevelButtonY


    private fun touchedPrizeButton(x: Int, y: Int) =
        x in prizeButtonX..(prizeButtonX + prizeTextBounds.width()) &&
                y in (prizeButtonY - prizeTextBounds.height())..prizeButtonY

    private fun touchedMainMenuButton(x: Int, y: Int) =
        x in mainMenuButtonX..(mainMenuButtonX + mainMenuTextBounds.width()) &&
                y in (mainMenuButtonY - mainMenuTextBounds.height()..mainMenuButtonY)

    private fun calcScore(): Int {
        val endTime = SystemClock.uptimeMillis()
        val delta = endTime - startTime
        val deltaInSeconds = (delta / 1000).toInt()
        return 1000 * (numBricks / deltaInSeconds)
    }
}