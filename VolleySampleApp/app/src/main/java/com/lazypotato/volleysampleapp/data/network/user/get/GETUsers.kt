package com.lazypotato.volleysampleapp.data.network.user.get

import android.content.Context
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.lazypotato.volleysampleapp.base.api.APIRequest
import com.lazypotato.volleysampleapp.base.api.APIRequestBuilder
import com.lazypotato.volleysampleapp.base.api.BaseAPIStringVolley
import com.lazypotato.volleysampleapp.data.network.user.model.User
import com.lazypotato.volleysampleapp.data.network.util.ErrorHandler
import com.lazypotato.volleysampleapp.data.network.util.NetworkConstants
import com.lazypotato.volleysampleapp.util.LogUtil
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class GETUsers(context: Context, listener: UserResponseListener) : BaseAPIStringVolley() {

    private val TAG = "GET USERS"

    private var context: Context = context
    private var listener: UserResponseListener = listener

    private val url: String = NetworkConstants.userGET()

    fun requestUsersList() {
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

    override fun onSuccess(response: String?) {
        LogUtil.debug(TAG, response.toString())

        val jsonArray = JSONArray(response)
        var users: MutableList<User> = mutableListOf()

        for(i in 0 until jsonArray.length()) {
            var user = Gson().fromJson(jsonArray[i].toString(), User::class.java)
            users.add(user)
        }

        listener.onUsersResponse(users,200)
    }

    override fun onError(error: VolleyError?) {
        val errorMessage: String = ErrorHandler.handleError(context, error)

        if (error != null) {
            listener.onUsersResponse(mutableListOf(), error.networkResponse.statusCode)
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