package com.lazypotato.user_notifications.utils

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import com.lazypotato.user_notifications.R


class SnackbarUtil {
    companion object {
        fun display(layout: CoordinatorLayout, msg: String) {
            val snackbar = Snackbar.make(layout, "Simple Snackbar",
                Snackbar.LENGTH_SHORT)

            snackbar.show()
        }

        fun displayWithButton(layout: CoordinatorLayout, msg: String) {
            val snackbar = Snackbar.make(layout, "Simple Snackbar With Action",
                Snackbar.LENGTH_SHORT)
            snackbar.setAction("UNDO", View.OnClickListener {
                LogUtil.debug("SnackbarUtil", "UNDO Action")
            })
            snackbar.show()
        }

        fun displayCustom(
            layoutInflater: LayoutInflater,
            layout: CoordinatorLayout,
            msg: String
        ) {
            val snackbar = Snackbar.make(layout, "", Snackbar.LENGTH_LONG)
            val customSnackView: View =
                layoutInflater.inflate(R.layout.layout_snackbar, null)
            snackbar.view.setBackgroundColor(Color.TRANSPARENT)

            val snackbarLayout = snackbar.view as SnackbarLayout
            snackbarLayout.setPadding(0, 0, 0, 0)

            val button: Button = customSnackView.findViewById(R.id.button)
            val title: TextView = customSnackView.findViewById(R.id.title)
            val info: TextView = customSnackView.findViewById(R.id.msg)

            title.text = "Information"
            info.text = msg
            button.setOnClickListener(View.OnClickListener {
                snackbar.dismiss()
            })

            snackbarLayout.addView(customSnackView, 0)

            snackbar.show()
        }
    }
}