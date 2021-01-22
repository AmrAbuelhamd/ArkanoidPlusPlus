package com.blogspot.soyamr.arkanoidplusplus.menu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.GameActivity
import com.blogspot.soyamr.arkanoidplusplus.notifications.NotificationEventReceiver
import com.blogspot.soyamr.arkanoidplusplus.notifications.NotificationIntentService


class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        NotificationEventReceiver.setupAlarm(applicationContext)

//        NotificationIntentService.createIntentStartNotificationService(this)
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("username", "Amr")
        intent.putExtra("level",4)
        startActivity(intent)
    }

/*    fun onSendNotificationsButtonClick(view: View?) {
        val notificationEventReceiver: NotificationEventReceiver = NotificationEventReceiver()
        notificationEventReceiver.setupAlarm(applicationContext)
    }*/

    override fun onBackPressed() {

    }
}