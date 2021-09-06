package com.lazypotato.workmanager_notifications.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import android.app.NotificationManager
import com.lazypotato.workmanager_notifications.SampleActivity

class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        print("Snooze Pressed")

        val extras = intent!!.extras

        extras?.let {
            val id = extras?.getInt("EXTRA_NOTIFICATION_ID")
            val ns = Context.NOTIFICATION_SERVICE
            val notificationManager = context!!.getSystemService(ns) as NotificationManager
            notificationManager?.cancel(0)

            val i = Intent(context, SampleActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra("TAP_BUTTON", id)
            }

            context?.startActivity(i)
        }
    }
}