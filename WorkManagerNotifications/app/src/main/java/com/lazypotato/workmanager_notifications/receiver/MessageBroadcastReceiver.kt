package com.lazypotato.workmanager_notifications.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.RemoteInput
import com.lazypotato.workmanager_notifications.model.Message
import com.lazypotato.workmanager_notifications.notifications.MessageNotification

class MessageBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val bundle = RemoteInput.getResultsFromIntent(intent)

        bundle?.let {
            val replyText = bundle.getCharSequence(MessageNotification.KEY_TEXT_REPLY)

            replyText?.let {
                val answer = Message(replyText, "Me")

                context?.let {
                    MessageNotification(context).show(answer)
                }
            }
        }
    }
}