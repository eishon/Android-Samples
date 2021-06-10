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
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class GETPosts @Inject constructor(
    private val context: Context,
    private val errorHandler: ErrorHandler
) : BaseAPIStringVolley() {

    private val TAG = "GET POSTS"

    private lateinit var listener: PostResponseListener

    private val url: String = NetworkConstants.postGET()

    fun requestPostsList(listener: PostResponseListener) {
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
        val errorMessage: String = errorHandler.handleError(context, error)

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