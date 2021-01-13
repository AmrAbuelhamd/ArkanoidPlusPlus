package com.blogspot.soyamr.arkanoidplusplus.game_stuff

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.ViewGroup
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.Level
import com.blogspot.soyamr.arkanoidplusplus.menu.MainActivity


class GameActivity : Activity() {
    lateinit var gameSurface: GameSurface
    var firstTime: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val height = getHeight(this)
        val width = getWidth(this)
        firstTime = true;
        gameSurface = GameSurface(this, height, width, Level.FIRST)
        gameSurface.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        setContentView(gameSurface)
    }

    private fun getWidth(context: Context): Int {
        var width: Int = 0
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val displayMetrics = DisplayMetrics()
            val display: Display? = context.getDisplay()
            display!!.getRealMetrics(displayMetrics)
            displayMetrics.widthPixels
        } else {
            val displayMetrics = DisplayMetrics()
            this.windowManager.defaultDisplay.getMetrics(displayMetrics)
            width = displayMetrics.widthPixels
            width
        }
    }

    private fun getHeight(context: Context): Int {
        var height: Int = 0
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val displayMetrics = DisplayMetrics()
            val display = context.display
            display!!.getRealMetrics(displayMetrics)
            displayMetrics.heightPixels
        } else {
            val displayMetrics = DisplayMetrics()
            this.windowManager.defaultDisplay.getMetrics(displayMetrics)
            height = displayMetrics.heightPixels
            height
        }
    }

    override fun onPause() {
        super.onPause()
        gameSurface.pause()
    }


    fun showMainMenu() {
        val intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
        val threadId = Thread.currentThread().id
        println("Thread # $threadId is doing this task")
    }

    fun saveUserScores(score: Int, levelNum: Int) {
        Log.i("game activity", " score $score levelNumber $levelNum")
    }

}