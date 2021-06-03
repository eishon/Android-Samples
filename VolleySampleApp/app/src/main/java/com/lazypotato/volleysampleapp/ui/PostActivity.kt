package com.lazypotato.volleysampleapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lazypotato.volleysampleapp.data.network.post.get.GETPosts
import com.lazypotato.volleysampleapp.data.network.post.model.Post
import com.lazypotato.volleysampleapp.data.network.post.get.PostResponseListener
import com.lazypotato.volleysampleapp.data.network.user.GETUsers
import com.lazypotato.volleysampleapp.data.network.user.UserResponseListener
import com.lazypotato.volleysampleapp.data.network.user.model.User
import com.lazypotato.volleysampleapp.databinding.ActivityPostBinding
import com.lazypotato.volleysampleapp.ui.adapter.PostRecyclerViewAdapter


class PostActivity : AppCompatActivity(),
    PostResponseListener, UserResponseListener {

    lateinit var binding: ActivityPostBinding

    private var userMap = mutableMapOf<Int, User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.title = "Posts"

        setRecyclerView()

        GETUsers(applicationContext, this).requestUsersList()

    }

    private fun setRecyclerView() {
        binding.postRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onPostResponse(posts: List<Post>, responseCode: Int) {
        val adapter = PostRecyclerViewAdapter(applicationContext, posts, userMap)
        binding.postRecyclerView.adapter = adapter
    }

    override fun onUsersResponse(users: List<User>, responseCode: Int) {
        userMap = mutableMapOf<Int, User>()
        for(user in users){
            userMap[user.id] = user
        }

        GETPosts(applicationContext, this).requestPostsList()
    }

    override fun showProgress(flag: Boolean) {
        if(flag)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }
}