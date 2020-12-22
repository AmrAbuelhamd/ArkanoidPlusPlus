package com.blogspot.soyamr.arkanoidplusplus

import android.graphics.Canvas
import android.view.SurfaceHolder
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class GameThread(private val gameSurface: Controller) {
    private val waitTime: Long = 100
    val executor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
    private val surfaceHolder: SurfaceHolder? = gameSurface.getHolder()
    var canvas: Canvas? = null
    private var lastTime: Long = 0
    private var delta: Long = 0
    private var frameCount = 0
    private fun initialize() {
        lastTime = System.currentTimeMillis()
        frameRate = "FPS 0"
    }

    private fun calculate() {
        val current = System.currentTimeMillis()
        delta += current - lastTime
        lastTime = current
        frameCount++
        if (delta > 1000) {
            delta -= 1000
            frameRate = String.format("FPS %s", frameCount)
            frameCount = 0
        }
    }

    private fun run() {
        println(Thread.currentThread().name)
        calculate()
        if (!surfaceHolder!!.surface.isValid) {
            return
        }
        canvas = surfaceHolder.lockCanvas()
        gameSurface.update()
        gameSurface.invalidate()
        surfaceHolder.unlockCanvasAndPost(canvas)
        //
    }

    companion object {
        var frameRate: String? = null
            private set
    }

    init {
        initialize()
        executor.scheduleWithFixedDelay(
            { this@GameThread.run() },
            0,
            waitTime,
            TimeUnit.MILLISECONDS
        )
    }
}