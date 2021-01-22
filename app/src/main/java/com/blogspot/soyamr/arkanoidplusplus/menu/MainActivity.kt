package com.blogspot.soyamr.arkanoidplusplus.menu

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.Repository
import com.blogspot.soyamr.arkanoidplusplus.databinding.ActivityMainBinding
import com.blogspot.soyamr.arkanoidplusplus.notifications.ExitNotification
import com.blogspot.soyamr.arkanoidplusplus.notifications.NotificationEventReceiver
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    lateinit var binding: ActivityMainBinding

    private var isMusicOn = false
    private var isExiting = false

    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotificationEventReceiver.setupAlarm(applicationContext)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.lifecycleOwner = this


        viewModel.isMusicOn.observe(this, {
            if (it) {
                mediaPlayer = MediaPlayer.create(applicationContext, R.raw.for_credits);
                isMusicOn = true
            }
        })

        viewModel.isExiting.observe(this, {
            if (it) {
                isExiting = true
            }
        })


        if (isMusicOn)
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.for_main_menu);
    }

    override fun onPause() {
        super.onPause()
        try {
            if (isMusicOn)
                mediaPlayer?.pause();
        } catch (i: Exception) {

        }
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

    override fun onBackPressed() {
        if (isExiting) {
            startService(Intent(this, ExitNotification::class.java))
            viewModel.settingsSetExitNotification(false)
        }
        //repository.SettingsSetExitNotification(true)

        stopMusic()
        finish()
    }

    fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }

    fun startMusic() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.for_main_menu);
        mediaPlayer?.start()
    }
}