package com.blogspot.soyamr.arkanoidplusplus

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.GameActivity

class LevelSelectActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_select)

        startActivity(Intent(this, GameActivity::class.java))
    }
}