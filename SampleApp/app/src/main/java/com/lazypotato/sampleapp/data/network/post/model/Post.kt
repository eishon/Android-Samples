package com.lazypotato.volleysampleapp.data.network.post.model

import java.io.Serializable

data class Post(
    var id: Int,
    var userId: Int,
    var title: String,
    var body: String
) : Serializable