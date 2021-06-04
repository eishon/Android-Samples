package com.lazypotato.volleysampleapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lazypotato.volleysampleapp.data.network.comment.CommentResponseListener
import com.lazypotato.volleysampleapp.data.network.comment.GETComments
import com.lazypotato.volleysampleapp.data.network.comment.model.Comment
import com.lazypotato.volleysampleapp.databinding.ActivityCommentBinding
import com.lazypotato.volleysampleapp.ui.adapter.CommentRecyclerViewAdapter

class CommentActivity : AppCompatActivity(), CommentResponseListener {

    private lateinit var binding: ActivityCommentBinding

    private var postId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.title = "Comments"

        getPostId()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setRecyclerView()

        if(postId != -1) {
            GETComments(applicationContext, this, postId).requestCommentsList()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return true;
    }

    private fun getPostId() {
        postId = intent.getIntExtra("POST_ID", -1)
    }

    private fun setRecyclerView() {
        binding.commentRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCommentResponse(comments: List<Comment>, responseCode: Int) {
        val adapter = CommentRecyclerViewAdapter(applicationContext, comments)
        binding.commentRecyclerView.adapter = adapter
    }

    override fun showProgress(flag: Boolean) {
        if(flag)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }
}