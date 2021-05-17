package com.lazypotato.user_notifications.utils

import android.util.Log

class LogUtil {
    companion object {
        fun debug(tag: String, msg: String) {
            Log.d(tag, msg)
        }

        fun error(tag: String, msg: String) {
            Log.e(tag, msg)
        }

        fun info(tag: String, msg: String) {
            Log.i(tag, msg)
        }

        fun warning(tag: String, msg: String) {
            Log.w(tag, msg)
        }
    }
}