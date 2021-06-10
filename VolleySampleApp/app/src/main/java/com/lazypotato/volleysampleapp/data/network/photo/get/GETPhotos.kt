package com.lazypotato.volleysampleapp.data.network.photo.get

import android.content.Context
import android.provider.ContactsContract
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.lazypotato.volleysampleapp.base.api.APIRequest
import com.lazypotato.volleysampleapp.base.api.APIRequestBuilder
import com.lazypotato.volleysampleapp.base.api.BaseAPIStringVolley
import com.lazypotato.volleysampleapp.data.network.photo.model.Photo
import com.lazypotato.volleysampleapp.data.network.post.model.Post
import com.lazypotato.volleysampleapp.data.network.util.ErrorHandler
import com.lazypotato.volleysampleapp.data.network.util.NetworkConstants
import com.lazypotato.volleysampleapp.util.LogUtil
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class GETPhotos @Inject constructor(
    private val context: Context,
    private val errorHandler: ErrorHandler
) : BaseAPIStringVolley() {

    private val TAG = "GET PHOTOS"

    private lateinit var listener: PhotoResponseListener

    private lateinit var url: String

    fun requestPhotosList(listener: PhotoResponseListener,albumId: Int) {
        this.url = NetworkConstants.photosGET(albumId)
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
        var photos: MutableList<Photo> = mutableListOf()

        for(i in 0 until jsonArray.length()) {
            var photo = Gson().fromJson(jsonArray[i].toString(), Photo::class.java)
            photos.add(photo)
        }

        listener.onPhotoResponse(photos,200)
    }

    override fun onError(error: VolleyError?) {
        val errorMessage: String = errorHandler.handleError(context, error)

        if (error != null) {
            listener.onPhotoResponse(mutableListOf(), error.networkResponse.statusCode)
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