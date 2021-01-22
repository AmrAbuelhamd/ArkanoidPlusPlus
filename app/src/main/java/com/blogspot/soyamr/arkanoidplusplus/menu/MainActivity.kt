package com.blogspot.soyamr.arkanoidplusplus.menu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.Repository
import com.blogspot.soyamr.arkanoidplusplus.notifications.ExitNotification
import com.blogspot.soyamr.arkanoidplusplus.notifications.NotificationEventReceiver


class MainActivity : FragmentActivity() {

    // repository
    private lateinit var repository: Repository
    private var isMusicOn: Boolean = false
    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotificationEventReceiver.setupAlarm(applicationContext)
        repository = Repository(this)
        isMusicOn = repository.SettingsGetMusic()

        setContentView(R.layout.activity_main)

        if (isMusicOn)
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.for_main_menu);
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
    override fun onBackPressed() {
        if (repository.SettingsGetExitNotification())
        {
            startService(Intent(this, ExitNotification::class.java))
            repository.SettingsSetExitNotification(false)
        }
        //repository.SettingsSetExitNotification(true)
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