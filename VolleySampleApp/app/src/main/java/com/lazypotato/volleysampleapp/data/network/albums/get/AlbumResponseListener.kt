package com.lazypotato.volleysampleapp.data.network.albums.get

import com.lazypotato.volleysampleapp.base.api.ProgressListener
import com.lazypotato.volleysampleapp.data.network.albums.model.Album

interface AlbumResponseListener: ProgressListener {
    fun onAlbumResponse(albums: List<Album>, responseCode: Int)
}