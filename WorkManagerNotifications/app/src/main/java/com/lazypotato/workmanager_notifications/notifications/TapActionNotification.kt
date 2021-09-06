package com.lazypotato.workmanager_notifications.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.lazypotato.workmanager_notifications.receiver.MyBroadcastReceiver
import com.lazypotato.workmanager_notifications.SampleActivity
import workmanager_notifications.R

class TapActionNotification {

    constructor (context: Context) {
        this.context = context

        createNotificationChannel()
    }

    private var context: Context

    private val CHANNEL_ID = "com.lazypotato.workmanager_notifications.notifications.tap_action"

    private val ACTION_SNOOZE = "com.lazypotato.workmanager_notifications.notifications.tap_action.snooze"
    private val EXTRA_NOTIFICATION_ID = "EXTRA_NOTIFICATION_ID"

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
        // Create an explicit intent for an Activity in your app
        val intent = Intent(context, SampleActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, builder.build())
        }
    }

    fun show(notificationId:Int, title: String, content: String, code: Int) {
        val intent = Intent(context, SampleActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(context,
            0, intent, 0)

        val snoozeBroadcastIntent = Intent(context, MyBroadcastReceiver::class.java).apply {
           action = ACTION_SNOOZE
            putExtra(EXTRA_NOTIFICATION_ID, code)
        }

        val actionPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(context, 0,
                snoozeBroadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_snooze, context.getString(R.string.snooze),
                actionPendingIntent)

        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, builder.build())
        }
    }
}