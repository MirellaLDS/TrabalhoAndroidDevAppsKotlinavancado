package com.example.mirella.trabalho_android_dev_apps_kotlin_avancado

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build

class NotificationUtil (var context: Context) {
    lateinit var mNotification: Notification

    val NOTIFICATION_ID = 1000

    fun notifyUtil() {
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notifyIntent = Intent(context, ResultActivity::class.java)

        val title = "Sample Notification"
        val message = "You have received a sample NotificationUtil. This NotificationUtil will take you to the details page."

        notifyIntent.putExtra("title", title)
        notifyIntent.putExtra("message", message)
        notifyIntent.putExtra("NotificationUtil", true)

        notifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        val pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val res = context.resources
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(MainActivity.CHANNEL_ID, MainActivity.CHANNEL_NAME, importance)
            notificationManager.createNotificationChannel(notificationChannel)


            mNotification = Notification.Builder(context, MainActivity.CHANNEL_ID)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.notification_template_icon_bg)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .setStyle(Notification.BigTextStyle()
                            .bigText(message))
                    .setContentTitle(title)
                    .setContentText(message).build()
        } else {

            mNotification = Notification.Builder(context)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.notification_template_icon_bg)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentTitle(title)
                    .setStyle(Notification.BigTextStyle()
                            .bigText(message))
                    .setSound(uri)
                    .setContentText(message).build()

        }

        notificationManager.notify(NOTIFICATION_ID, mNotification)
    }

}