package com.lazypotato.user_notifications.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.widget.TextView
import com.lazypotato.user_notifications.R

class DialogUtil {
    companion object{
        fun displayAlertDialog(activity: Activity, msg: String){
            val builder: AlertDialog.Builder? = activity?.let {
                AlertDialog.Builder(it)
            }

            builder?.apply {
                setTitle("Information")
                setMessage(msg)
                setPositiveButton(
                    "Ok",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User clicked OK button
                    })
                setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            }

            val alert = builder!!.create()
            alert.show()

        }

        fun displayCustomDialog(activity: Activity, msg: String){
            val builder = AlertDialog.Builder(activity)

            val inflater = activity.layoutInflater;
            val view = inflater.inflate(R.layout.layout_dialog, null)

            (view.findViewById(R.id.msg) as TextView).text = msg

            builder.setView(view)
                // Add action buttons
                .setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialog, id ->
                        // sign in the user ...
                    })
                .setNegativeButton("CANCEL",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })
            builder.create()

            val alert = builder!!.create()
            alert.show()
        }
    }
}