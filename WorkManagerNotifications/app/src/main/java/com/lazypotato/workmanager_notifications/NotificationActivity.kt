package com.lazypotato.workmanager_notifications

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import workmanager_notifications.R
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.lazypotato.workmanager_notifications.notifications.*


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
                "Big Text Notification",
                "Big Text Notification Content",
                "This is a large text which will be shown in this notification." +
                        "This is a large text which will be shown in this notification." +
                        "This is a large text which will be shown in this notification." +
                        "This is a large text which will be shown in this notification." +
                        "This is a large text which will be shown in this notification."
            )
        }

        findViewById<Button>(R.id.basicNotificationLargeImage).setOnClickListener {
            Glide.with(this)
                .asBitmap()
                .load("https://picsum.photos/seed/picsum/720")
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        BasicNotification(applicationContext).show(
                            3,
                            "Big Image Notification",
                            "Big Image Content",
                            resource
                        )
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // this is called when imageView is cleared on lifecycle call or for
                        // some other reason.
                        // if you are referencing the bitmap somewhere else too other than this imageView
                        // clear it here as you can no longer have the bitmap
                    }
                })
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

        findViewById<Button>(R.id.replyButtonAction).setOnClickListener {
            MessageNotification(applicationContext).show(3)
        }

        findViewById<Button>(R.id.progressNotification).setOnClickListener {
            ProgressNotification(applicationContext).show(
                1,
                "Progress Notification",
                "Progress Notification Content"
            )
        }

        findViewById<Button>(R.id.inboxMessaging).setOnClickListener {
            Glide.with(this)
                .asBitmap()
                .load("https://picsum.photos/seed/picsum/720")
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        MessageNotification(applicationContext).show(
                            1,
                            "Adam",
                            "Messaging Notification Content",
                            resource
                        )
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // this is called when imageView is cleared on lifecycle call or for
                        // some other reason.
                        // if you are referencing the bitmap somewhere else too other than this imageView
                        // clear it here as you can no longer have the bitmap
                    }
                })
        }

        findViewById<Button>(R.id.conversationMessaging).setOnClickListener {
            MessageNotification(applicationContext).show(
                2,
                "Adam"
            )
        }

        findViewById<Button>(R.id.mediaNotification).setOnClickListener {
            Glide.with(this)
                .asBitmap()
                .load("https://picsum.photos/seed/picsum/720")
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        MediaNotification(applicationContext).show(
                            1,
                            "My Song",
                            "Awesome Album",
                            resource
                        )
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // this is called when imageView is cleared on lifecycle call or for
                        // some other reason.
                        // if you are referencing the bitmap somewhere else too other than this imageView
                        // clear it here as you can no longer have the bitmap
                    }
                })
        }

        findViewById<Button>(R.id.callNotification).setOnClickListener {
            ScheduledNotification(applicationContext).show(
                1
            )
        }
    }
}