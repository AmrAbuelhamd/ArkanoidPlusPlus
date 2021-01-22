package com.blogspot.soyamr.arkanoidplusplus.notifications

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.blogspot.soyamr.arkanoidplusplus.menu.MainActivity

class ExitNotification: Service() {

    private val notificationID = 101
    private val channelID = "my_notification"

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        sendNotification()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(channelID, "go away title", NotificationManager.IMPORTANCE_DEFAULT).apply{
                description = "bye to user"
            }
            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(){
        val intent = Intent(this, MainActivity::class.java)
        intent.apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val builder = NotificationCompat.Builder(this, channelID)
        builder
            .setContentTitle("Already leaving? Ladno")
            .setAutoCancel(true)
            .setColor(resources.getColor(R.color.darker_gray))
            .setContentText("come over when you're sober")
            .setSmallIcon(R.drawable.star_on)
            .setContentIntent(pendingIntent)

        with(NotificationManagerCompat.from(this)){
            notify(notificationID, builder.build())
        }
    }

}