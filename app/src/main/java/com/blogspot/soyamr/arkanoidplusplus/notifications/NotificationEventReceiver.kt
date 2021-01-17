package com.blogspot.soyamr.arkanoidplusplus.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.legacy.content.WakefulBroadcastReceiver
import java.util.*


class NotificationEventReceiver(notificationIntentService: NotificationIntentService) : WakefulBroadcastReceiver() {

    var notificationIntentService: NotificationIntentService = notificationIntentService

    private val ACTION_START_NOTIFICATION_SERVICE = "ACTION_START_NOTIFICATION_SERVICE"
    private val ACTION_DELETE_NOTIFICATION = "ACTION_DELETE_NOTIFICATION"
    private val NOTIFICATIONS_INTERVAL_IN_HOURS = 2

    fun setupAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = getStartPendingIntent(context)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            getTriggerAt(Date()),
            NOTIFICATIONS_INTERVAL_IN_HOURS * AlarmManager.INTERVAL_HOUR,
            alarmIntent
        )
    }

    override fun onReceive(context: Context?, intent: Intent) {
        val action = intent.action
        var serviceIntent: Intent? = null
        if (ACTION_START_NOTIFICATION_SERVICE == action) {
            Log.i(javaClass.simpleName, "onReceive from alarm, starting notification service")
            serviceIntent = notificationIntentService.createIntentStartNotificationService(context)
        } else if (ACTION_DELETE_NOTIFICATION == action) {
            Log.i(
                javaClass.simpleName,
                "onReceive delete notification action, starting notification service to handle delete"
            )
            serviceIntent = notificationIntentService.createIntentDeleteNotification(context)
        }
        if (serviceIntent != null) {
            startWakefulService(context, serviceIntent)
        }
    }

    private fun getTriggerAt(now: Date): Long {
        val calendar: Calendar = Calendar.getInstance()
        calendar.setTime(now)
        //calendar.add(Calendar.HOUR, NOTIFICATIONS_INTERVAL_IN_HOURS);
        return calendar.getTimeInMillis()
    }

    private fun getStartPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, NotificationEventReceiver::class.java)
        intent.action = ACTION_START_NOTIFICATION_SERVICE
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    fun getDeleteIntent(context: Context?): PendingIntent? {
        val intent = Intent(context, NotificationEventReceiver::class.java)
        intent.action = ACTION_DELETE_NOTIFICATION
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}