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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotificationEventReceiver.setupAlarm(applicationContext)
        repository = Repository(this)
        setContentView(R.layout.activity_main)
    }

/*    fun onSendNotificationsButtonClick(view: View?) {
        val notificationEventReceiver: NotificationEventReceiver = NotificationEventReceiver()
        notificationEventReceiver.setupAlarm(applicationContext)
    }*/

    override fun onBackPressed() {
        if (repository.SettingsGetExitNotification())
        {
            startService(Intent(this, ExitNotification::class.java))
            repository.SettingsSetExitNotification(false)
        }
        repository.SettingsSetExitNotification(true)
        finish()
    }
}