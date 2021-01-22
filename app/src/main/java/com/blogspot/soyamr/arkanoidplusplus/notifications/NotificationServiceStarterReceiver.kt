package com.blogspot.soyamr.arkanoidplusplus.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.*


class NotificationServiceStarterReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        NotificationEventReceiver.setupAlarm(context!!)
    }
}