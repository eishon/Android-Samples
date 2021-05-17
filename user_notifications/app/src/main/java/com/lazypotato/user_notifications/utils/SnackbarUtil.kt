package com.lazypotato.user_notifications.utils

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar


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

        fun displayCustom(layout: CoordinatorLayout, msg: String) {

        }
    }
}