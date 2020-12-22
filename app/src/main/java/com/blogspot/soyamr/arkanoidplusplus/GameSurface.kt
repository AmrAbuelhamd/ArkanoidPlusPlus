package com.blogspot.soyamr.arkanoidplusplus

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameSurface(context: Context) : SurfaceView(context),
    SurfaceHolder.Callback, Controller {
    private var gameThread: GameThread? = null
    lateinit var model: IModel

    init {

        // Make Game Surface focusable so it can handle events.
        this.setFocusable(true)

        // Set callback.

        // Set callback.
        this.holder!!.addCallback(this)

        model = Model(context)
    }

    override fun update() {
        //update something
        model.update()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        canvas.save()

        //draw something

        model.draw(canvas)


        val paint = Paint()

        paint.setTextSize(60f)
        paint.setAntiAlias(true)
        paint.setColor(Color.YELLOW)
        paint.setStyle(Paint.Style.FILL)

        canvas.drawText(
            GameThread.frameRate.toString(),
            150F,
           150F,
            paint
        )

        canvas.restore()
    }


    // Implements method of SurfaceHolder.Callback
    override fun surfaceCreated(holder: SurfaceHolder) {
        resume()
        setWillNotDraw(false)
    }

    // Implements method of SurfaceHolder.Callback
    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    // Implements method of SurfaceHolder.Callback
    override fun surfaceDestroyed(holder: SurfaceHolder) {
        pause()
    }

    fun pause() {
        gameThread?.executor?.shutdown()
    }

    fun resume() {
        gameThread = GameThread(this)
    }
}