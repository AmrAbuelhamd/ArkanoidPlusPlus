package com.blogspot.soyamr.arkanoidplusplus

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView

class ScoreActivity : Activity() {
    companion object {
        val SCORE = "score"
    }

    // animated background
    var animationDrawable: AnimationDrawable? = null
    var frameLayout: FrameLayout? = null
    // animated background

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        // declare animation and frameLayout
        frameLayout=findViewById(R.id.scoreFrameLay)
        animationDrawable= frameLayout!!.background as AnimationDrawable?
        // add time changes
        animationDrawable!!.setEnterFadeDuration(5000)
        animationDrawable!!.setExitFadeDuration(2000)
        // start animation
        animationDrawable!!.start()

        findViewById<TextView>(R.id.currScoreTextView).apply {
            text = "${intent.getIntExtra(SCORE, 0)}"
        }
    }
}