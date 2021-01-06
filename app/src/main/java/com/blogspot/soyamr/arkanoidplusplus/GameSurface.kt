package com.blogspot.soyamr.arkanoidplusplus

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.blogspot.soyamr.arkanoidplusplus.model.IModel
import com.blogspot.soyamr.arkanoidplusplus.model.Model

class GameSurface(context: Context) : SurfaceView(context),
    SurfaceHolder.Callback, Controller, IGameSurface {
    private lateinit var gameThread: GameThread
    var model: IModel

    init {
        // Make Game Surface focusable so it can handle events.
        this.isFocusable = true

        this.holder.addCallback(this)

        model = Model(context, this)
    }

    override fun update() {
        model.update()
    }

    override fun drawScene(canvas: Canvas) {

        canvas.save()
        canvas.drawColor(Color.WHITE)
        model.draw(canvas)
        canvas.restore()

    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        resume()
        setWillNotDraw(false)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        pause()
    }

    fun pause() {
        var retry = true
        while (retry) {
            try {
                gameThread.setRunning(false)
                gameThread.join()
                retry = false
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }

    fun resume() {
        gameThread = GameThread(this)
        gameThread.setRunning(true)
        gameThread.start()
    }
}