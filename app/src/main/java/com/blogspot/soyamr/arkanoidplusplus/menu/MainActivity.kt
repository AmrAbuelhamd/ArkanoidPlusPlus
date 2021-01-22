package com.blogspot.soyamr.arkanoidplusplus.menu

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.fragment.app.FragmentActivity
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.notifications.NotificationEventReceiver
import com.blogspot.soyamr.arkanoidplusplus.notifications.NotificationIntentService


class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NotificationEventReceiver.setupAlarm(applicationContext)

        NotificationIntentService.createIntentStartNotificationService(this)
    }

/*    fun onSendNotificationsButtonClick(view: View?) {
        val notificationEventReceiver: NotificationEventReceiver = NotificationEventReceiver()
        notificationEventReceiver.setupAlarm(applicationContext)
    }*/

    override fun onBackPressed() {

    }
}