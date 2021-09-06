package com.lazypotato.workmanager_notifications

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lazypotato.workmanager_notifications.notifications.BasicNotification
import com.lazypotato.workmanager_notifications.notifications.MessageNotification
import com.lazypotato.workmanager_notifications.notifications.ProgressNotification
import com.lazypotato.workmanager_notifications.notifications.TapActionNotification
import workmanager_notifications.R

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        supportActionBar?.title = "Notification"

        findViewById<Button>(R.id.basicNotification).setOnClickListener {
            BasicNotification(applicationContext).show(
                1,
                "Basic Notification",
                "Basic Notification Content"
            )
        }

        findViewById<Button>(R.id.basicNotificationLargeText).setOnClickListener {
            BasicNotification(applicationContext).show(
                2,
                "Basic Notification",
                "Basic Notification Content",
                "This is a large text which will be shown in this notification." +
                        "This is a large text which will be shown in this notification." +
                        "This is a large text which will be shown in this notification." +
                        "This is a large text which will be shown in this notification." +
                        "This is a large text which will be shown in this notification."
            )
        }

        findViewById<Button>(R.id.tapAction).setOnClickListener {
            TapActionNotification(applicationContext).show(
                1,
                "Tap Action Notification",
                "Tap Action Notification Content"
            )
        }

        findViewById<Button>(R.id.tapButtonAction).setOnClickListener {
            TapActionNotification(applicationContext).show(
                2,
                "Tap Button Action Notification",
                "Tap Button Action Notification Content",
                1234
            )
        }

        findViewById<Button>(R.id.messagingNotification).setOnClickListener {
            MessageNotification(applicationContext).show(
                1,
                "Messaging Notification",
                "Messaging Notification Content"
            )
        }

        findViewById<Button>(R.id.progressNotification).setOnClickListener {
            ProgressNotification(applicationContext).show(
                1,
                "Progress Notification",
                "Progress Notification Content"
            )
        }
    }
}