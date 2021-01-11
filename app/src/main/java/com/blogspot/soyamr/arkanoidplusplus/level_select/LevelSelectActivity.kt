package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.GameActivity

class LevelSelectActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_select)

        //startActivity(Intent(this, GameActivity::class.java))
    }
}