package com.blogspot.soyamr.arkanoidplusplus.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.notifications.NotificationEventReceiver
import com.blogspot.soyamr.arkanoidplusplus.notifications.NotificationIntentService


class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NotificationEventReceiver.setupAlarm(applicationContext)
    }

/*    fun onSendNotificationsButtonClick(view: View?) {
        val notificationEventReceiver: NotificationEventReceiver = NotificationEventReceiver()
        notificationEventReceiver.setupAlarm(applicationContext)
    }*/

    override fun onBackPressed() {

    }
}