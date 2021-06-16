package com.lazypotato.volleysampleapp.data.network.photo.get

import com.lazypotato.volleysampleapp.base.api.ProgressListener
import com.lazypotato.volleysampleapp.data.network.photo.model.Photo

interface PhotoResponseListener: ProgressListener {
    fun onPhotoResponse(photos: List<Photo>, responseCode: Int)
}