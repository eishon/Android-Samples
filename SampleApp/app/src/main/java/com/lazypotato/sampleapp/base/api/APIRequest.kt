package com.lazypotato.volleysampleapp.base.api

import android.content.Context
import com.lazypotato.volleysampleapp.util.LogUtil.debug
import org.json.JSONObject

class APIRequest(builder: APIRequestBuilder) {
    //required
    val context: Context
    val method: Int
    val url: String

    //optional
    var tag = "API REQUEST"
    var jsonObject: JSONObject? = null
    var authHeaders: Map<String, String>? = null

    override fun toString(): String {
        var text = ""
        text += "APIRequest{"
        text += "context=$context"
        text += ", method=$method"
        text += ", url='$url'"
        text += ", tag='$tag'"
        if (jsonObject != null) text += ", jsonObject=$jsonObject"
        text += '}'
        return text
    }

    init {
        context = builder.context
        method = builder.method
        url = builder.url
        tag = builder.tag
        jsonObject = builder.jsonObject
        authHeaders = builder.authHeaders
        debug(tag, toString())
    }
}