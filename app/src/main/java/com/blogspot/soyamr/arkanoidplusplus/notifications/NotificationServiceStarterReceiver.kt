package com.blogspot.soyamr.arkanoidplusplus.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.*


class NotificationServiceStarterReceiver(notificationEventReceiver: NotificationEventReceiver) : BroadcastReceiver() {


    var notificationEventReceiver: NotificationEventReceiver = notificationEventReceiver

    override fun onReceive(context: Context?, intent: Intent?) {
        notificationEventReceiver.setupAlarm(context!!)
    }
}