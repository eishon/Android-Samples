package com.lazypotato.volleysampleapp.data.network.todos.get

import android.content.Context
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.lazypotato.volleysampleapp.base.api.APIRequest
import com.lazypotato.volleysampleapp.base.api.APIRequestBuilder
import com.lazypotato.volleysampleapp.base.api.BaseAPIStringVolley
import com.lazypotato.volleysampleapp.data.network.post.model.Post
import com.lazypotato.volleysampleapp.data.network.todos.model.ToDo
import com.lazypotato.volleysampleapp.data.network.util.ErrorHandler
import com.lazypotato.volleysampleapp.data.network.util.NetworkConstants
import com.lazypotato.volleysampleapp.util.LogUtil
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class GETToDos @Inject constructor(
    private val context: Context,
    private val errorHandler: ErrorHandler
) : BaseAPIStringVolley() {

    private val TAG = "GET TODOS"

    private lateinit var listener: ToDoResponseListener

    private lateinit var url: String

    fun requestToDosList(listener: ToDoResponseListener, userId: Int) {
        this.url = NetworkConstants.todosGET(userId)
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
        var todos: MutableList<ToDo> = mutableListOf()

        for(i in 0 until jsonArray.length()) {
            var todo = Gson().fromJson(jsonArray[i].toString(), ToDo::class.java)
            todos.add(todo)
        }

        listener.onToDoResponse(todos,200)
    }

    override fun onError(error: VolleyError?) {
        val errorMessage: String = errorHandler.handleError(context, error)

        if (error != null) {
            listener.onToDoResponse(mutableListOf(), error.networkResponse.statusCode)
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