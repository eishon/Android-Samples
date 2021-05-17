package com.lazypotato.user_notifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.lazypotato.user_notifications.databinding.ActivityMainBinding
import com.lazypotato.user_notifications.utils.ToastUtil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupToast()
    }

    private fun setupToast() {
        binding.simpleToast.setOnClickListener {
            ToastUtil.display(applicationContext, "This is a Simple Toast")
        }

        binding.customToast.setOnClickListener {
            ToastUtil.displayCustomToast(applicationContext, "This is a Custom Toast")
        }
    }
}