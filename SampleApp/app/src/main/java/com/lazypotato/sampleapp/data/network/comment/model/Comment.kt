package com.lazypotato.volleysampleapp.data.network.comment.model

import java.io.Serializable

data class Comment(
    var id: Int,
    var postId: Int,
    var name: String,
    var email: String,
    var body: String,
):Serializable
