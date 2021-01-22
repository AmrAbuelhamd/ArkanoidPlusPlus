package com.blogspot.soyamr.arkanoidplusplus.notifications

import android.R
import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.legacy.content.WakefulBroadcastReceiver
import com.blogspot.soyamr.arkanoidplusplus.menu.MainActivity


class NotificationIntentService : IntentService(NotificationIntentService::class.java.simpleName) {
    override fun onHandleIntent(intent: Intent?) {
        Log.d(javaClass.simpleName, "onHandleIntent, started handling a notification event")
        try {
            val action = intent!!.action
            if (ACTION_START == action) {
                processStartNotification()
            }
            if (ACTION_DELETE == action) {
                processDeleteNotification(intent)
            }
        } finally {
            WakefulBroadcastReceiver.completeWakefulIntent(intent)
        }
    }

    private fun processDeleteNotification(intent: Intent?) {
        // Log something?
    }

    private fun processStartNotification() {
        // Do something. For example, fetch fresh data from backend to create a rich notification?
/*        val builder = NotificationCompat.Builder(this)
        builder.setContentTitle("Scheduled Notification")
            .setAutoCancel(true)
            .setColor(resources.getColor(R.color.darker_gray))
            .setContentText("This notification has been triggered by Notification Service")
            .setSmallIcon(R.drawable.star_on)
        val pendingIntent = PendingIntent.getActivity(
            this,
            NOTIFICATION_ID,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(pendingIntent)
        builder.setDeleteIntent(NotificationEventReceiver.getDeleteIntent(this))
        val manager = this.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, builder.build())*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, "remind", NotificationManager.IMPORTANCE_DEFAULT).apply{
                description = "remind to user"
            }
            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }


        val pendingIntent = PendingIntent.getActivity(
            this,
            NOTIFICATION_ID,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
        builder
            .setContentTitle("IT WOOOOORKS")
            .setAutoCancel(true)
            .setColor(resources.getColor(R.color.darker_gray))
            .setContentText("aeeeeeeeee")
            .setSmallIcon(R.drawable.star_on)
            .setContentIntent(pendingIntent)

        with(NotificationManagerCompat.from(this)){
            notify(NOTIFICATION_ID, builder.build())
        }

    }

    companion object {
        const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "this_is_example"
        private const val ACTION_START = "ACTION_START"
        private const val ACTION_DELETE = "ACTION_DELETE"
        fun createIntentStartNotificationService(context: Context?): Intent {
            val intent = Intent(context, NotificationIntentService::class.java)
            intent.action = ACTION_START
            return intent
        }

        fun createIntentDeleteNotification(context: Context?): Intent {
            val intent = Intent(context, NotificationIntentService::class.java)
            intent.action = ACTION_DELETE
            return intent
        }
    }
}