package com.lazypotato.workmanager_notifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import workmanager_notifications.R

class SampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        supportActionBar?.title = "Sample Activity"

        val extras = intent!!.extras

        extras?.let {
            val id = extras?.getInt("TAP_BUTTON")

            findViewById<TextView>(R.id.textView).text = "ID: $id"
        }
    }
}