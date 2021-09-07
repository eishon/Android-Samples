package com.lazypotato.workmanager_notifications.model

data class Message(
    val text: CharSequence,
    val sender: CharSequence,
    val timestamp: Long = System.currentTimeMillis()
)
