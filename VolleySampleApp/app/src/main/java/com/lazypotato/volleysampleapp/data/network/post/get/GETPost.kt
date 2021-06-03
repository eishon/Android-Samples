package com.lazypotato.volleysampleapp.data.network.post.get

import android.app.ProgressDialog
import android.content.Context
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.lazypotato.volleysampleapp.R
import com.lazypotato.volleysampleapp.base.api.APIRequest
import com.lazypotato.volleysampleapp.base.api.APIRequestBuilder
import com.lazypotato.volleysampleapp.base.api.BaseAPIVolley
import com.lazypotato.volleysampleapp.data.network.util.ErrorHandler
import com.lazypotato.volleysampleapp.data.network.util.NetworkConstants
import com.lazypotato.volleysampleapp.util.LogUtil
import com.lazypotato.volleysampleapp.util.PrefManager
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class GETPost(context: Context, listener: PostResponseListener) : BaseAPIVolley() {

    private val TAG = "GET POSTS"

    private var context: Context = context
    private var listener: PostResponseListener = listener

    private val url: String = NetworkConstants.postGET()

    fun requestPostsList() {
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

    override fun onSuccess(response: JSONObject?) {
        LogUtil.debug(TAG, response.toString())

        var posts: MutableList<Post> = mutableListOf();

//        for(){
//            val post = Gson().fromJson(response.toString(), Post::class.java)
//            posts.add(post)
//        }

        listener.onPostResponse(posts,200)
    }

    override fun onError(error: VolleyError?) {
        val errorMessage: String = ErrorHandler.handleError(context, error)

        if (error != null) {
            listener.onPostResponse(mutableListOf(), error.networkResponse.statusCode)
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