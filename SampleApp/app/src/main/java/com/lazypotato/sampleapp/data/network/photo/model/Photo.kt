package com.lazypotato.volleysampleapp.data.network.photo.model

import java.io.Serializable

data class Photo(
    var  albumId: Int,
    var  id: Int,
    var  title: String,
    var  url: String,
    var  thumbnailUrl: String
): Serializable
