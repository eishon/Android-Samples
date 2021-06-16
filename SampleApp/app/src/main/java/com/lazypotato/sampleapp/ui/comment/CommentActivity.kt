package com.lazypotato.volleysampleapp.ui.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lazypotato.volleysampleapp.data.network.comment.get.CommentResponseListener
import com.lazypotato.volleysampleapp.data.network.comment.get.GETComments
import com.lazypotato.volleysampleapp.data.network.comment.model.Comment
import com.lazypotato.volleysampleapp.databinding.ActivityCommentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CommentActivity : AppCompatActivity(), CommentResponseListener {

    private lateinit var binding: ActivityCommentBinding

    @Inject lateinit var getComments: GETComments

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
            getComments.requestCommentsList(this, postId)
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