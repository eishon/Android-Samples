package com.lazypotato.volleysampleapp.base.api

import android.content.Context
import org.json.JSONObject

class APIRequestBuilder(//required
    val context: Context, val method: Int, val url: String
) {

    //optional
    var tag = "API REQUEST"
        private set
    var jsonObject: JSONObject? = null
        private set
    var authHeaders: Map<String, String>? = null
        private set

    fun setTag(tag: String): APIRequestBuilder {
        this.tag = tag
        return this
    }

    fun setJsonObject(jsonObject: JSONObject?): APIRequestBuilder {
        this.jsonObject = jsonObject
        return this
    }

    fun setAuthHeaders(authHeaders: Map<String, String>?): APIRequestBuilder {
        this.authHeaders = authHeaders
        return this
    }

    fun build(): APIRequest {
        return APIRequest(this)
    }
}