package com.lazypotato.user_notifications

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lazypotato.user_notifications.databinding.ActivityMainBinding
import com.lazypotato.user_notifications.utils.DialogUtil
import com.lazypotato.user_notifications.utils.LocalNotificationUtil
import com.lazypotato.user_notifications.utils.SnackbarUtil
import com.lazypotato.user_notifications.utils.ToastUtil


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var image: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        image = BitmapFactory.decodeResource(
            resources,
            R.mipmap.white
        )

        setupToast()

        setupSnackbar()

        setupDialog()

        setupNotification()
    }

    private fun setupToast() {
        binding.simpleToast.setOnClickListener {
            ToastUtil.display(applicationContext, "This is a Simple Toast")
        }

        binding.customToast.setOnClickListener {
            ToastUtil.displayCustomToast(applicationContext, "This is a Custom Toast")
        }
    }

    private fun setupSnackbar() {
        binding.simpleSnackbar.setOnClickListener {
            SnackbarUtil.display(binding.coordinatorLayout, "This is a Simple Snackbar")
        }

        binding.simpleSnackbarWithAction.setOnClickListener {
            SnackbarUtil.displayWithButton(binding.coordinatorLayout, "This is a Simple Snackbar with Action")
        }

        binding.customSnackbar.setOnClickListener {
            SnackbarUtil.displayCustom(layoutInflater, binding.coordinatorLayout, "This is a Custom Snackbar")
        }
    }

    private fun setupDialog(){
        binding.simpleAlertDialog.setOnClickListener {
            DialogUtil.displayAlertDialog(this, "This is Simple Alert Dialog")
        }

        binding.customAlertDialog.setOnClickListener {
            DialogUtil.displayCustomDialog(this, "This is Custom Dialog")
        }
    }

    private fun setupNotification() {
        binding.simpleNotification.setOnClickListener {
            LocalNotificationUtil(applicationContext).display("Information", "This is simple notification")
        }

        binding.expandedNotification.setOnClickListener {


            LocalNotificationUtil(applicationContext).displayBigPictureNotification(
                "Information",
                "This is simple notification",
                image
            )
        }

        binding.customNotification.setOnClickListener {

        }
    }
}