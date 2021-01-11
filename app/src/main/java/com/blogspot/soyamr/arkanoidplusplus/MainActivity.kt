package com.blogspot.soyamr.arkanoidplusplus

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout

class MainActivity : Activity() {

    var startButton: Button?=null
    var settingsButton: Button?=null
    var scoreButton: Button?=null


    // animated background
    var animationDrawable: AnimationDrawable? = null
    var frameLayout:FrameLayout? = null
    // animated background

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // declare animation and frameLayout
        frameLayout=findViewById(R.id.mainFrameLay)
        animationDrawable= frameLayout!!.background as AnimationDrawable?
        // add time changes
        animationDrawable!!.setEnterFadeDuration(5000)
        animationDrawable!!.setExitFadeDuration(2000)
        // start animation
        animationDrawable!!.start()

        startButton = findViewById(R.id.buttonStart)
        settingsButton = findViewById(R.id.buttonSettings)
        scoreButton = findViewById(R.id.buttonScore)

        startButton!!.setOnClickListener{
            startActivity(Intent(this, LevelSelectActivity::class.java))
        }

        settingsButton!!.setOnClickListener{
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        scoreButton!!.setOnClickListener{
            startActivity(Intent(this, ScoreActivity::class.java))
        }

        //
    }

}