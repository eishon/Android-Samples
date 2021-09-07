package com.lazypotato.workmanager_notifications.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.media.app.NotificationCompat as MediaNotificationCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.lazypotato.workmanager_notifications.receiver.MediaBroadcastReceiver
import workmanager_notifications.R

class MediaNotification {

    constructor (context: Context) {
        this.context = context

        createNotificationChannel()
    }

    private var context: Context

    private val CHANNEL_ID = "com.lazypotato.workmanager_notifications.notifications.media"

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

    fun show(notificationId: Int, musicTitle: String, content: String, bitmap: Bitmap) {
        val prevPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(
                context,
                0,
                Intent(context, MediaBroadcastReceiver::class.java).apply {
                    putExtra("ACTION", -1)
                },
                PendingIntent.FLAG_UPDATE_CURRENT
            )

        val pausePendingIntent: PendingIntent =
            PendingIntent.getBroadcast(
                context,
                1,
                Intent(context, MediaBroadcastReceiver::class.java).apply {
                    putExtra("ACTION", 0)
                },
                PendingIntent.FLAG_UPDATE_CURRENT
            )

        val nextPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(
                context,
                2,
                Intent(context, MediaBroadcastReceiver::class.java).apply {
                    putExtra("ACTION", 1)
                },
                PendingIntent.FLAG_UPDATE_CURRENT
            )

        var notification = NotificationCompat.Builder(context, CHANNEL_ID)
            // Show controls on lock screen even when user hides sensitive content.
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.notification_icon)
            .addAction(R.drawable.ic_dislike, "Dislike",null)
            .addAction(R.drawable.ic_prev, "Previous",prevPendingIntent)
            .addAction(R.drawable.ic_pause, "Pause", pausePendingIntent)
            .addAction(R.drawable.ic_next, "Next", nextPendingIntent)
            .addAction(R.drawable.ic_like, "Like",null)
            .setStyle(MediaNotificationCompat.MediaStyle()
                .setShowActionsInCompactView(1,2,3)
                //.setMediaSession(mediaSession.sessionToken)
            )
            .setContentTitle(musicTitle)
            .setContentText(content)
            .setLargeIcon(bitmap)
            .build()

        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, notification)
        }
    }
}