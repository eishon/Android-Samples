package com.lazypotato.workmanager_notifications.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MediaBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val extras = intent!!.extras

        extras?.let {
            val id = extras?.getInt("ACTION")

            Toast.makeText(
                context,
                when (id) {
                    0 -> "pause"
                    -1 -> "prev"
                    1 -> "next"
                    else -> "Not Recognised"
                },
                Toast.LENGTH_SHORT
            ).show()

        }
    }
}