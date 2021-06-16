package com.lazypotato.volleysampleapp.data.network.albums.model

import java.io.Serializable

data class Album(
    var id: Int,
    var userId: Int,
    var title: String
    ): Serializable
