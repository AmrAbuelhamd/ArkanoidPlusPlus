package com.blogspot.soyamr.arkanoidplusplus.menu

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.blogspot.soyamr.arkanoidplusplus.CongratulationsActivity
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.Repository
import com.blogspot.soyamr.arkanoidplusplus.notifications.ExitNotification
import com.blogspot.soyamr.arkanoidplusplus.notifications.NotificationEventReceiver
import java.lang.Exception


class MainActivity : FragmentActivity() {

    // repository
    private lateinit var repository: Repository
    private var isMusicOn: Boolean = false
    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotificationEventReceiver.setupAlarm(applicationContext)

        val intent = Intent(this, CongratulationsActivity::class.java)
        startActivity(intent)
        repository = Repository(this)
        isMusicOn = repository.settingsGetMusic()

        setContentView(R.layout.activity_main)

        if (isMusicOn)
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.for_main_menu);
    }

    override fun onPause() {
        super.onPause()
        try {
            if (isMusicOn)
                mediaPlayer?.pause();
        }
        catch (i:Exception){

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
        if (repository.settingsGetExitNotification())
        {
            startService(Intent(this, ExitNotification::class.java))
            repository.settingsSetExitNotification(false)
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