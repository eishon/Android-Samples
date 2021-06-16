package com.lazypotato.volleysampleapp.ui.post

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lazypotato.volleysampleapp.R
import com.lazypotato.volleysampleapp.data.network.post.model.Post
import com.lazypotato.volleysampleapp.data.network.user.model.User
import com.lazypotato.volleysampleapp.ui.comment.CommentActivity
import com.lazypotato.volleysampleapp.ui.user.UserActivity

class PostRecyclerViewAdapter (
    private val context: Context,
    private val posts: List<Post>,
    private var userMap: Map<Int, User>
) :
    RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_post_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.title.text = posts[position].title
        viewHolder.name.text = userMap[posts[position].userId]?.name ?: ""
        viewHolder.body.text = posts[position].body

        viewHolder.name.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, UserActivity::class.java).apply {
                putExtra("USER_ID", posts[position].userId)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            ContextCompat.startActivity(context, intent, null)
        })

        viewHolder.comments.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, CommentActivity::class.java).apply {
                putExtra("POST_ID", posts[position].id)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            ContextCompat.startActivity(context, intent, null)
        })
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = posts.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val name: TextView = view.findViewById(R.id.userName)
        val body: TextView = view.findViewById(R.id.body)
        val comments: TextView = view.findViewById(R.id.comments)
    }
}
