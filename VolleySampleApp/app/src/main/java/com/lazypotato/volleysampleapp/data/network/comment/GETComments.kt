package com.lazypotato.volleysampleapp.data.network.comment

import android.content.Context
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.lazypotato.volleysampleapp.base.api.APIRequest
import com.lazypotato.volleysampleapp.base.api.APIRequestBuilder
import com.lazypotato.volleysampleapp.base.api.BaseAPIStringVolley
import com.lazypotato.volleysampleapp.data.network.comment.model.Comment
import com.lazypotato.volleysampleapp.data.network.post.model.Post
import com.lazypotato.volleysampleapp.data.network.util.ErrorHandler
import com.lazypotato.volleysampleapp.data.network.util.NetworkConstants
import com.lazypotato.volleysampleapp.util.LogUtil
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class GETComments(
    context: Context,
    listener: CommentResponseListener,
    postId: Int
) : BaseAPIStringVolley() {

    private val TAG = "GET POSTS"

    private var context: Context = context
    private var listener: CommentResponseListener = listener

    private val url: String = NetworkConstants.commentsGET(postId)

    fun requestCommentsList() {
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
        var comments: MutableList<Comment> = mutableListOf()

        for(i in 0 until jsonArray.length()) {
            var comment = Gson().fromJson(jsonArray[i].toString(), Comment::class.java)
            comments.add(comment)
        }

        listener.onCommentResponse(comments,200)
    }

    override fun onError(error: VolleyError?) {
        val errorMessage: String = ErrorHandler.handleError(context, error)

        if (error != null) {
            listener.onCommentResponse(mutableListOf(), error.networkResponse.statusCode)
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