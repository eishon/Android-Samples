package com.lazypotato.workmanager_notifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import workmanager_notifications.R

class WorkManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        supportActionBar?.title = "Work Manager"
    }
}