package com.lazypotato.workmanager_notifications.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import com.lazypotato.workmanager_notifications.model.Message
import com.lazypotato.workmanager_notifications.receiver.MessageBroadcastReceiver
import workmanager_notifications.R

class MessageNotification {

    companion object {
        val KEY_TEXT_REPLY = "key_text_reply"

        private val sender1 = "Adam"
        private val sender2 = "Sam"

        private val messages = mutableListOf<Message>(
            Message("Good morning", sender1),
            Message("Hello", ""),
            Message("Hi!!!", sender2)
        )
    }

    constructor (context: Context) {
        this.context = context

        createNotificationChannel()
    }

    private var context: Context

    private val CHANNEL_ID = "com.lazypotato.workmanager_notifications.notifications.message"



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

    // inbox-style notification
    fun show(notificationId: Int, sender: String, content: String, bitmap: Bitmap) {
        val messageSnippet1 = "$sender: Hello, how are you?"
        val messageSnippet2 = "You: I am fine. What about you?"

        var notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_mail)
            .setContentTitle("New mails from $sender")
            .setContentText(content)
            .setLargeIcon(bitmap)
            .setStyle(
                NotificationCompat.InboxStyle()
                    .addLine(messageSnippet1)
                    .addLine(messageSnippet2)
            )
            .build()

        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, notification)
        }
    }

    // conversation in a notification
    fun show(notificationId: Int, sender: String) {
        val messages = listOf<NotificationCompat.MessagingStyle.Message>(
            NotificationCompat.MessagingStyle.Message(
                "Hello, how are you?",
                1630980918L,
                "$sender"
            ),
            NotificationCompat.MessagingStyle.Message(
                "I am fine. What about you?",
                1630980978L,
                "You"
            )
        )

        var notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_mail)
            .setStyle(
                NotificationCompat
                    .MessagingStyle(context.resources.getString(R.string.reply_name))
                    .addMessage(messages[0])
                    .addMessage(messages[1])
            )
            .build()

        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, notification)
        }
    }

    // with reply
    fun show(message: Message) {
        messages.add(message)

        show(3)
    }

    fun show(notificationId: Int) {

        val remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY)
            .setLabel("Your answer...")
            .build()

        val replyIntent = Intent(context, MessageBroadcastReceiver::class.java)
        val replyPendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            replyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val replyAction = NotificationCompat.Action.Builder(
            R.drawable.ic_reply,
            "Reply",
            replyPendingIntent
        ).addRemoteInput(remoteInput).build()

        val messagingStyle = NotificationCompat.MessagingStyle("Me")
        messagingStyle.conversationTitle = "Group Chat"

        messages.forEach {
            val notificationMessage = NotificationCompat.MessagingStyle.Message(
                it.text, it.timestamp, it.sender
            )

            messagingStyle.addMessage(notificationMessage)
        }

        var notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_mail)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .addAction(replyAction)
            .setColor(Color.BLUE)
            .setStyle(messagingStyle)
            .build()

        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, notification)
        }
    }
}