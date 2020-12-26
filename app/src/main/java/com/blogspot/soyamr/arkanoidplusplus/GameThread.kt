package com.blogspot.soyamr.arkanoidplusplus

import android.graphics.Canvas
import android.view.SurfaceHolder

class GameThread(private val gameSurface: Controller) : Thread() {
    private val surfaceHolder: SurfaceHolder = gameSurface.getHolder()
    private var canvas: Canvas? = null
    private var running = false;

    override fun run() {
        var startTime = System.nanoTime()
        while (running) {
            gameSurface.update()
            try {
                canvas = surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    if (canvas != null) {
//                        gameSurface.draw(canvas!!)//freezing canvas
                        gameSurface.invalidate()//working {but at certain moments the ball is moving faster for 1 or two seconds}
                    }
                }
            } finally {
                if (canvas != null)
                    surfaceHolder.unlockCanvasAndPost(canvas)
            }
            val now = System.nanoTime()

            var waitTime: Long = (now - startTime) / 1000000
            if (waitTime < 100) {
                waitTime = 100 - waitTime
                try {
                    sleep(waitTime)
                } catch (ignored: InterruptedException) {
                    ignored.printStackTrace()
                }
            }

            startTime = System.nanoTime()
        }
    }

    fun setRunning(running: Boolean) {
        this.running = running;
    }
}