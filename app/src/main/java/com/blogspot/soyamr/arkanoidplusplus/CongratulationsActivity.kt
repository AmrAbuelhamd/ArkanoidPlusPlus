package com.blogspot.soyamr.arkanoidplusplus

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle

class CongratulationsActivity : Activity() {
    private var isMusicOn: Boolean = false
    var mediaPlayer: MediaPlayer? = null
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congratulations)
        repository = Repository(this)
        isMusicOn = repository.SettingsGetMusic()

        if (isMusicOn)
            mediaPlayer = MediaPlayer.create(applicationContext, R.raw.for_credits);
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