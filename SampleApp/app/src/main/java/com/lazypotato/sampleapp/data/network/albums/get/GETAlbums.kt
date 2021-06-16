package com.lazypotato.volleysampleapp.data.network.albums.get

import android.content.Context
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.lazypotato.volleysampleapp.base.api.APIRequest
import com.lazypotato.volleysampleapp.base.api.APIRequestBuilder
import com.lazypotato.volleysampleapp.base.api.BaseAPIStringVolley
import com.lazypotato.volleysampleapp.data.network.albums.model.Album
import com.lazypotato.volleysampleapp.data.network.post.model.Post
import com.lazypotato.volleysampleapp.data.network.util.ErrorHandler
import com.lazypotato.volleysampleapp.data.network.util.NetworkConstants
import com.lazypotato.volleysampleapp.util.LogUtil
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class GETAlbums @Inject constructor(
    private val context: Context,
    private val errorHandler: ErrorHandler
) : BaseAPIStringVolley() {

    private val TAG = "GET ALBUMS"

    private lateinit var listener: AlbumResponseListener

    private lateinit var url: String

    fun requestAlbumsList(listener: AlbumResponseListener, userId: Int) {
        this.url = NetworkConstants.albumsGET(userId)
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
        var albums: MutableList<Album> = mutableListOf()

        for(i in 0 until jsonArray.length()) {
            var album = Gson().fromJson(jsonArray[i].toString(), Album::class.java)
            albums.add(album)
        }

        listener.onAlbumResponse(albums,200)
    }

    override fun onError(error: VolleyError?) {
        val errorMessage: String = errorHandler.handleError(context, error)

        if (error != null) {
            listener.onAlbumResponse(mutableListOf(), error.networkResponse.statusCode)
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