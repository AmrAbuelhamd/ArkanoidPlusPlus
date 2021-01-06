package com.blogspot.soyamr.arkanoidplusplus

import android.graphics.Canvas
import android.os.SystemClock
import android.view.SurfaceHolder

var avgFPS = 0
fun getAvgFPS(): String {
    return "fps: " + avgFPS
}

class GameThread(private val gameSurface: Controller) : Thread() {
    private val surfaceHolder: SurfaceHolder = gameSurface.getHolder()
    private var canvas: Canvas? = null
    private var running = false


    override fun run() {
        var startTime: Long
        var endTime: Long
        val targetWaitTime: Long = 100
        var actualWaitTime: Long
        var totalTime: Long = 0
        var frameCount = 0
        while (running) {
            startTime = SystemClock.uptimeMillis()

            gameSurface.update()
            try {
                canvas = surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    if (canvas != null) {
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
            totalTime += endTime - startTime
            ++frameCount
            if (totalTime > 1000) {
                avgFPS = frameCount
                println(avgFPS)
                totalTime -= 1000
                frameCount = 0
            }
        }
    }

    fun setRunning(running: Boolean) {
        this.running = running;
    }
}