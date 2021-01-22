package com.blogspot.soyamr.arkanoidplusplus.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.legacy.content.WakefulBroadcastReceiver
import androidx.legacy.content.WakefulBroadcastReceiver.startWakefulService
import java.util.*


class NotificationEventReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        var serviceIntent: Intent? = null
        if (ACTION_START_NOTIFICATION_SERVICE == action) {
            Log.i(javaClass.simpleName, "onReceive from alarm, starting notification service")
            serviceIntent = NotificationIntentService.createIntentStartNotificationService(context)
        } else if (ACTION_DELETE_NOTIFICATION == action) {
            Log.i(
                javaClass.simpleName,
                "onReceive delete notification action, starting notification service to handle delete"
            )
            serviceIntent = NotificationIntentService.createIntentDeleteNotification(context)
        }
        if (serviceIntent != null) {

            startWakefulService(context, serviceIntent)
        }
    }

    companion object {
        private const val ACTION_START_NOTIFICATION_SERVICE = "ACTION_START_NOTIFICATION_SERVICE"
        private const val ACTION_DELETE_NOTIFICATION = "ACTION_DELETE_NOTIFICATION"
        private const val NOTIFICATIONS_INTERVAL_IN_HOURS = 1
        fun setupAlarm(context: Context) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val alarmIntent = getStartPendingIntent(context)
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                getTriggerAt(Date()),
                NOTIFICATIONS_INTERVAL_IN_HOURS * 100000L,
                alarmIntent
            )
        }

        private fun getTriggerAt(now: Date): Long {
            val calendar = Calendar.getInstance()
            calendar.time = now
            calendar.add(Calendar.MINUTE, NOTIFICATIONS_INTERVAL_IN_HOURS);
            return calendar.timeInMillis
        }

        private fun getStartPendingIntent(context: Context): PendingIntent {
            val intent = Intent(context, NotificationEventReceiver::class.java)
            intent.action = ACTION_START_NOTIFICATION_SERVICE
            return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        fun getDeleteIntent(context: Context?): PendingIntent {
            val intent = Intent(context, NotificationEventReceiver::class.java)
            intent.action = ACTION_DELETE_NOTIFICATION
            return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }
}