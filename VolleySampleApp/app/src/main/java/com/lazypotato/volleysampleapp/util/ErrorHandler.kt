package com.lazypotato.volleysampleapp.util

import android.content.Context
import com.android.volley.AuthFailureError
import com.android.volley.TimeoutError
import com.android.volley.VolleyError

class ErrorHandler private constructor(context: Context) {
    companion object {
        private var errorHandler: ErrorHandler? = null

        @Synchronized
        fun getInstance(context: Context): ErrorHandler? {
            if (errorHandler == null) errorHandler = ErrorHandler(context)
            return errorHandler
        }

        fun handle(error: VolleyError?) {
            if (error is AuthFailureError) handleAuthError() else if (error is TimeoutError) handleTimeOutError()
        }

        private fun handleAuthError() {}
        private fun handleTimeOutError() {}
    }
}