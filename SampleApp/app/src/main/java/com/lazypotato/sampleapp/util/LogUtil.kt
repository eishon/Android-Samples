package com.lazypotato.volleysampleapp.util

import android.util.Log
import com.lazypotato.volleysampleapp.data.constant.AppConstants

object LogUtil {
    @JvmStatic
    fun debug(tag: String?, message: String) {
        if (AppConstants.DEBUG) Log.d(tag, "$message ")
    }

    @JvmStatic
    fun error(tag: String?, message: String?) {
        if (AppConstants.DEBUG) Log.e(tag, message!!)
    }
}