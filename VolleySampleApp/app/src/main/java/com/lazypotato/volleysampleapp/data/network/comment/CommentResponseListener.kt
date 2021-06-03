package com.lazypotato.volleysampleapp.data.network.comment

import com.lazypotato.volleysampleapp.base.api.ProgressListener
import com.lazypotato.volleysampleapp.data.network.comment.model.Comment
import com.lazypotato.volleysampleapp.data.network.post.model.Post

interface CommentResponseListener: ProgressListener {
    fun onCommentResponse(posts: List<Comment>, responseCode: Int)
}