package com.blogspot.soyamr.arkanoidplusplus.game_stuff

import android.graphics.Canvas
import android.os.SystemClock
import android.view.SurfaceHolder
import com.blogspot.soyamr.arkanoidplusplus.Controller


class GameThread(private val gameSurface: Controller) : Thread() {
    private val surfaceHolder: SurfaceHolder = gameSurface.getHolder()
    private var canvas: Canvas? = null
    private var running = false
    var fps = 1
    var paused = true

    companion object {
        var avgFPS = 0

        fun getAvgFPS(): String {
            return "fps: " + avgFPS
        }
    }

    override fun run() {
        var startTime: Long
        var endTime: Long
        val targetWaitTime: Long = 10
        var actualWaitTime: Long
        var totalTime: Long = 0
        var frameCount = 0
        var delta: Int
        while (running) {
            startTime = SystemClock.uptimeMillis()

            try {
                canvas = surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    if (canvas != null) {
                        if (!paused)
                            gameSurface.update(fps)
                        if (running)
                            gameSurface.drawScene(canvas!!)
                    }
                }
            } finally {
                if (canvas != null)
                    surfaceHolder.unlockCanvasAndPost(canvas)
            }

            endTime = SystemClock.uptimeMillis()

            actualWaitTime = endTime - startTime
            if (actualWaitTime < targetWaitTime) {
                actualWaitTime = targetWaitTime - actualWaitTime
                try {
                    sleep(actualWaitTime)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                endTime = SystemClock.uptimeMillis()
            }

            //to be used for smooth movements in the game
            delta = (endTime - startTime).toInt();

            if (delta >= 1) {
                fps = 1000 / delta
            }
            //to calculate average fps
            totalTime += delta
            ++frameCount
            if (totalTime > 1000) {
                avgFPS = frameCount
                totalTime -= 1000
                frameCount = 0
            }
        }
    }

    fun setRunning(running: Boolean) {
        this.running = running;
    }
}