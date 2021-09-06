package com.lazypotato.workmanager_notifications.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.CountDownTimer
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import workmanager_notifications.R

class ProgressNotification {
    constructor (context: Context) {
        this.context = context

        createNotificationChannel()
    }

    private var context: Context

    private val CHANNEL_ID = "com.lazypotato.workmanager_notifications.notifications.progress"

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
        val builder = NotificationCompat.Builder(context, CHANNEL_ID).apply {
            setContentTitle(title)
            setContentText(content)
            setSmallIcon(R.drawable.notification_icon)
            setPriority(NotificationCompat.PRIORITY_LOW)
        }
        val PROGRESS_MAX = 100
        var PROGRESS_CURRENT = 0
        NotificationManagerCompat.from(context).apply {
            // Issue the initial notification with zero progress
            builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false)
            notify(notificationId, builder.build())

            // Do the job here that tracks the progress.
            // Usually, this should be in a
            // worker thread
            // To show progress, update PROGRESS_CURRENT and update the notification with:
            // builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
            // notificationManager.notify(notificationId, builder.build());

            // When done, update the notification one more time to remove the progress bar
            // builder.setContentText("Download complete")
            //     .setProgress(0, 0, false)
            // notify(notificationId, builder.build())

            val total = 10000L
            val timer = object: CountDownTimer(total, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    PROGRESS_CURRENT = ((total - millisUntilFinished)/100).toInt()

                    builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
                    notify(notificationId, builder.build());
                }

                override fun onFinish() {
                    builder.setContentText("Download complete")
                         .setProgress(0, 0, false)
                     notify(notificationId, builder.build())
                }
            }
            timer.start()
        }
    }
}