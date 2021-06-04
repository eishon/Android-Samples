package com.lazypotato.volleysampleapp.ui.user.todos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lazypotato.volleysampleapp.R
import com.lazypotato.volleysampleapp.data.network.comment.model.Comment
import com.lazypotato.volleysampleapp.data.network.todos.model.ToDo

class ToDoRecyclerViewAdapter (
    private val context: Context,
    private val todos: List<ToDo>,
) :
    RecyclerView.Adapter<ToDoRecyclerViewAdapter.ViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_todo_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.title.text = todos[position].title
        viewHolder.title.isChecked = todos[position].completed
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = todos.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: CheckBox = view.findViewById(R.id.title)
    }
}
