package com.lazypotato.volleysampleapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lazypotato.volleysampleapp.R
import com.lazypotato.volleysampleapp.data.network.post.get.GETPost
import com.lazypotato.volleysampleapp.data.network.post.get.Post
import com.lazypotato.volleysampleapp.data.network.post.get.PostResponseListener

class MainActivity : AppCompatActivity(), PostResponseListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var getPost: GETPost = GETPost(applicationContext, this)

        getPost.requestPostsList()
    }

    override fun onPostResponse(posts: List<Post>, responseCode: Int) {

    }

    override fun showProgress(flag: Boolean) {

    }
}