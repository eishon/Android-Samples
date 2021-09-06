package com.lazypotato.workmanager_notifications.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.RemoteInput
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.lazypotato.workmanager_notifications.SampleActivity
import workmanager_notifications.R

class MessageNotification {

    constructor (context: Context) {
        this.context = context

        createNotificationChannel()
    }

    private var context: Context

    private val CHANNEL_ID = "com.lazypotato.workmanager_notifications.notifications.message"

    private val KEY_TEXT_REPLY = "key_text_reply"

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun show(notificationId:Int, title: String, content: String) {

    }
}