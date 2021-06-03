package com.lazypotato.volleysampleapp.base.api

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.lazypotato.volleysampleapp.data.network.util.VolleySingleton.Companion.getInstance
import com.lazypotato.volleysampleapp.util.LogUtil.debug
import org.json.JSONObject

abstract class BaseAPIVolley {
    var apiRequest: APIRequest? = null
        get() {
            if (field == null) {
                field = intAPIRequest()
            }
            return field
        }
        private set

    interface Method {
        companion object {
            const val DEPRECATED_GET_OR_POST = -1
            const val GET = 0
            const val POST = 1
            const val PUT = 2
            const val DELETE = 3
            const val HEAD = 4
            const val OPTIONS = 5
            const val TRACE = 6
            const val PATCH = 7
        }
    }

    protected abstract fun intAPIRequest(): APIRequest?
    protected abstract fun showProgress(flag: Boolean)
    protected abstract fun onSuccess(response: JSONObject?)
    protected abstract fun onError(error: VolleyError?)

    fun processRequest() {
        showProgress(true)
        debug(apiRequest!!.tag, apiRequest!!.url)
        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(
            apiRequest!!.method,
            apiRequest!!.url,
            apiRequest!!.jsonObject,
            Response.Listener { response ->
                showProgress(false)
                onSuccess(response)
            },
            Response.ErrorListener { error ->
                showProgress(false)
                onError(error)
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                return if (apiRequest!!.authHeaders != null) apiRequest!!.authHeaders!! else super.getHeaders()
            }
        }
        getInstance(apiRequest!!.context)
            ?.addToRequestQueue(jsonObjectRequest)
    }
}