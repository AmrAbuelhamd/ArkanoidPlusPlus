package com.blogspot.soyamr.arkanoidplusplus.level_select

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.Repository

class LoginAndLevelSelectActivity : FragmentActivity() {

    private lateinit var repository: Repository
    private var isMusicOn: Boolean = false
    var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_select)
        //startActivity(Intent(this, GameActivity::class.java))
        repository = Repository(this)
        isMusicOn = repository.settingsGetMusic()
        if (isMusicOn)
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.for_level_selector);
    }

    override fun onBackPressed() {
        finish()
    }
    override fun onPause() {
        super.onPause()
        if (isMusicOn)
            mediaPlayer?.pause();
    }

    override fun onResume() {
        super.onResume()
        if (isMusicOn)
            mediaPlayer?.start();
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isMusicOn)
            mediaPlayer?.release();
    }
}