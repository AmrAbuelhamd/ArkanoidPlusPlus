package com.blogspot.soyamr.arkanoidplusplus

import android.graphics.Canvas
import android.view.SurfaceHolder

class GameThread(private val gameSurface: Controller): Thread() {
    private val waitTime: Long = 100
    private val surfaceHolder: SurfaceHolder = gameSurface.getHolder()
    private lateinit var canvas: Canvas
    private var lastTime: Long = 0
    private var running = false;

    override fun run() {
        var startTime = System.nanoTime()
        while (running) {
            gameSurface.update()
            try {
                canvas = surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
//                    gameSurface.draw(canvas)
                    gameSurface.invalidate()
                }
            } finally {
                surfaceHolder.unlockCanvasAndPost(canvas)
            }
            val now = System.nanoTime()

            var waitTime: Long = (now - startTime) / 1000000
            if (waitTime < 100) {
                waitTime = 100 - waitTime
            }
            try {
                sleep(waitTime)
            } catch (ignored: InterruptedException) {
                ignored.printStackTrace()
            }
            startTime = System.nanoTime()
        }
    }

    fun setRunning(running: Boolean) {
        this.running = running;
    }
}