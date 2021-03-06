package com.lazypotato.volleysampleapp.data.network.user.get

import android.content.Context
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.lazypotato.volleysampleapp.base.api.APIRequest
import com.lazypotato.volleysampleapp.base.api.APIRequestBuilder
import com.lazypotato.volleysampleapp.base.api.BaseAPIJsonObjectVolley
import com.lazypotato.volleysampleapp.base.api.BaseAPIStringVolley
import com.lazypotato.volleysampleapp.data.network.user.model.User
import com.lazypotato.volleysampleapp.data.network.util.ErrorHandler
import com.lazypotato.volleysampleapp.data.network.util.NetworkConstants
import com.lazypotato.volleysampleapp.util.LogUtil
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class GETUserInfo @Inject constructor(
    private val context: Context,
    private val errorHandler: ErrorHandler
) : BaseAPIJsonObjectVolley() {

    private val TAG = "GET USER"

    private lateinit var listener: UserInfoResponseListener

    private lateinit var url: String

    fun requestUserInfo(listener: UserInfoResponseListener, userId: Int) {
        this.url = NetworkConstants.userInfoGET(userId)
        this.listener = listener

        processRequest();
    }

    override fun intAPIRequest(): APIRequest? {
        val builder: APIRequestBuilder = APIRequestBuilder(
            context,
            Method.GET,
            url
        )
            .setTag(TAG)
        return builder.build()
    }

    override fun showProgress(flag: Boolean) {
        listener.showProgress(flag)
    }

    override fun onSuccess(response: JSONObject) {
        LogUtil.debug(TAG, response.toString())

        val user = Gson().fromJson(response.toString(), User::class.java)

        listener.onUserInfoResponse(user,200)
    }

    override fun onError(error: VolleyError?) {
        val errorMessage: String = errorHandler.handleError(context, error)

        if (error != null) {
            listener.onUserInfoResponse(null, error.networkResponse.statusCode)
        }
    }

    private fun prepareJson(id: Int): JSONObject {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("id", id)

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonObject
    }
}