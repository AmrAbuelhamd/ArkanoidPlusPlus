package com.blogspot.soyamr.arkanoidplusplus

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.FrameLayout

class MainActivity : Activity() {

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

        //startActivity(Intent(this, LevelSelectActivity::class.java))
    }

}