package com.lazypotato.volleysampleapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lazypotato.volleysampleapp.R
import com.lazypotato.volleysampleapp.data.network.comment.model.Comment
import com.lazypotato.volleysampleapp.data.network.post.model.Post
import com.lazypotato.volleysampleapp.data.network.user.model.User
import com.lazypotato.volleysampleapp.ui.CommentActivity
import com.lazypotato.volleysampleapp.ui.UserActivity

class CommentRecyclerViewAdapter (
    private val context: Context,
    private val comments: List<Comment>,
) :
    RecyclerView.Adapter<CommentRecyclerViewAdapter.ViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_comment_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.name.text = comments[position].email
        viewHolder.body.text = comments[position].body
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = comments.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.userName)
        val body: TextView = view.findViewById(R.id.body)
    }
}
