package com.lazypotato.volleysampleapp.data.network.post.get

import android.content.Context
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.lazypotato.volleysampleapp.base.api.APIRequest
import com.lazypotato.volleysampleapp.base.api.APIRequestBuilder
import com.lazypotato.volleysampleapp.base.api.BaseAPIStringVolley
import com.lazypotato.volleysampleapp.data.network.post.model.Post
import com.lazypotato.volleysampleapp.data.network.util.ErrorHandler
import com.lazypotato.volleysampleapp.data.network.util.NetworkConstants
import com.lazypotato.volleysampleapp.util.LogUtil
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class GETPosts(context: Context, listener: PostResponseListener) : BaseAPIStringVolley() {

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

    override fun onSuccess(response: String?) {
        LogUtil.debug(TAG, response.toString())

        val jsonArray = JSONArray(response)
        var posts: MutableList<Post> = mutableListOf()

        for(i in 0 until jsonArray.length()) {
            var post = Gson().fromJson(jsonArray[i].toString(), Post::class.java)
            posts.add(post)
        }

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