package com.lazypotato.volleysampleapp.data.network.util

import android.content.Context
import com.android.volley.NoConnectionError
import com.android.volley.TimeoutError
import com.android.volley.VolleyError
import com.lazypotato.volleysampleapp.R
import com.lazypotato.volleysampleapp.data.constant.AppConstants
import com.lazypotato.volleysampleapp.util.LogUtil
import com.lazypotato.volleysampleapp.util.PrefManager
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class ErrorHandler @Inject constructor(
    private val prefManager: PrefManager,
) {

    fun handleError(context: Context, error: VolleyError?): String {
        val networkResponse = error?.networkResponse
        var errorMessage = context.getString(R.string.try_again_later)
        if (networkResponse == null) {
            if (error!!.javaClass == TimeoutError::class.java) {
                errorMessage = context.getString(R.string.timeout_error)
            } else if (error.javaClass == NoConnectionError::class.java) {
                errorMessage = context.getString(R.string.failed_to_connect)
            }
        } else {
            val result = String(networkResponse.data)
            try {
                val response = JSONObject(result)
                val status = response.getString("status")
                val message = response.getString("message")
                LogUtil.error("Error Status", status)
                LogUtil.error("Error Message", message)
                if (networkResponse.statusCode == 404) {
                    errorMessage = context.getString(R.string.resource_not_found)
                } else if (networkResponse.statusCode == 401) {
                    prefManager.clearUserData()
                    errorMessage = context.getString(R.string.please_login_again)
                } else if (networkResponse.statusCode == 400) {
                    errorMessage = context.getString(R.string.invalid_user_id)
                } else if (networkResponse.statusCode == 500) {
                    errorMessage = context.getString(R.string.internal_server_error)
                }
            } catch (e: JSONException) {
                if (AppConstants.DEBUG) e.printStackTrace()
            }
        }
        return errorMessage
    }

    private fun handleAuthError() {}
    private fun handleTimeOutError() {}
}