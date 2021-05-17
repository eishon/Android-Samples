package com.lazypotato.user_notifications.utils

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.lazypotato.user_notifications.R

class ToastUtil {
    companion object {
        fun display(context: Context, msg: String) {
            val toast = Toast.makeText(context, msg, Toast.LENGTH_LONG)
            toast.show()
        }

        fun displayCustomToast(
            context: Context,
            msg: String,
        ) {
            val toast: Toast = Toast.makeText(context, msg, Toast.LENGTH_LONG)
            val view: View = LayoutInflater.from(context)
                .inflate(R.layout.layout_custom_toast, null)
            toast.setView(view)
            val text = view.findViewById<TextView>(R.id.toast_txt_vw)
            text.setText(msg)
            toast.show()
        }
    }
}