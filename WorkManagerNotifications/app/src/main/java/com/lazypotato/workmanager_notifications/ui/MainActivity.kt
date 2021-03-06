package com.lazypotato.workmanager_notifications.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.lazypotato.workmanager_notifications.ui.notification.NotificationActivity
import com.lazypotato.workmanager_notifications.ui.workmanager.WorkManagerActivity
import workmanager_notifications.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.notification_btn).setOnClickListener {
            startActivity(Intent(applicationContext, NotificationActivity::class.java))
        }

        findViewById<Button>(R.id.work_manager_btn).setOnClickListener {
            startActivity(Intent(applicationContext, WorkManagerActivity::class.java))
        }
    }
}