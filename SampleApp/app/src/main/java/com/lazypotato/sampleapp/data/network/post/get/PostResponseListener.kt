package com.lazypotato.volleysampleapp.data.network.post.get

import com.lazypotato.volleysampleapp.base.api.ProgressListener
import com.lazypotato.volleysampleapp.data.network.post.model.Post

interface PostResponseListener: ProgressListener {
    fun onPostResponse(posts: List<Post>, responseCode: Int)
}