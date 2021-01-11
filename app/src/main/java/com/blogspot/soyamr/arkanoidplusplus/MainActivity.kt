package com.blogspot.soyamr.arkanoidplusplus

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import android.view.ViewGroup

class MainActivity : Activity() {
    lateinit var gameSurface: GameSurface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val height = getHeight(this)
        val width = getWidth(this)

        gameSurface = GameSurface(this, height, width)
        gameSurface.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        setContentView(gameSurface)
    }

    fun getWidth(context: Context): Int {
        var width: Int = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val displayMetrics = DisplayMetrics()
            val display: Display? = context.getDisplay()
            display!!.getRealMetrics(displayMetrics)
            return displayMetrics.widthPixels
        } else {
            val displayMetrics = DisplayMetrics()
            this.windowManager.defaultDisplay.getMetrics(displayMetrics)
            width = displayMetrics.widthPixels
            return width
        }
    }

    fun getHeight(context: Context): Int {
        var height: Int = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val displayMetrics = DisplayMetrics()
            val display = context.display
            display!!.getRealMetrics(displayMetrics)
            return displayMetrics.heightPixels
        } else {
            val displayMetrics = DisplayMetrics()
            this.windowManager.defaultDisplay.getMetrics(displayMetrics)
            height = displayMetrics.heightPixels
            return height
        }
    }

    override fun onPause() {
        super.onPause()
        gameSurface.pause()
    }

    override fun onResume() {
        super.onResume()
        gameSurface.resume()
    }
}