package com.blogspot.soyamr.arkanoidplusplus.menu

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.blogspot.soyamr.arkanoidplusplus.LevelSelectActivity
import com.blogspot.soyamr.arkanoidplusplus.R

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}