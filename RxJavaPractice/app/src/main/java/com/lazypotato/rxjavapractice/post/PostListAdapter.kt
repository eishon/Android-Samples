package com.lazypotato.rxjavapractice.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lazypotato.rxjavapractice.R
import com.lazypotato.rxjavapractice.model.Post

class PostListAdapter(

): RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    private var posts: List<Post> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_post_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = posts[position].title
        holder.name.text = posts[position].id.toString()
        holder.body.text = posts[position].body
    }

    override fun getItemCount() = posts.size

    fun setData(postList: List<Post>) {
        posts = postList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val name: TextView = view.findViewById(R.id.userName)
        val body: TextView = view.findViewById(R.id.body)
        val comments: TextView = view.findViewById(R.id.comments)
    }
}