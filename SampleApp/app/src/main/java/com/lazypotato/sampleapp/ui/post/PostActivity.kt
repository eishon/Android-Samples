package com.lazypotato.volleysampleapp.ui.post

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lazypotato.volleysampleapp.data.network.post.get.GETPosts
import com.lazypotato.volleysampleapp.data.network.post.model.Post
import com.lazypotato.volleysampleapp.data.network.post.get.PostResponseListener
import com.lazypotato.volleysampleapp.data.network.user.get.GETUsers
import com.lazypotato.volleysampleapp.data.network.user.get.UserResponseListener
import com.lazypotato.volleysampleapp.data.network.user.model.User
import com.lazypotato.volleysampleapp.databinding.ActivityPostBinding
import com.lazypotato.volleysampleapp.ui.post.PostRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostActivity : AppCompatActivity(),
    PostResponseListener, UserResponseListener {

    lateinit var binding: ActivityPostBinding

    @Inject lateinit var getPosts: GETPosts
    @Inject lateinit var getUsers: GETUsers

    private var userMap = mutableMapOf<Int, User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.title = "Posts"

        setRecyclerView()

        getUsers.requestUsersList(this)

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

        getPosts.requestPostsList(this)
    }

    override fun showProgress(flag: Boolean) {
        if(flag)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }
}