package com.lazypotato.volleysampleapp.ui.user.album

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lazypotato.volleysampleapp.R
import com.lazypotato.volleysampleapp.data.network.albums.model.Album
import com.lazypotato.volleysampleapp.ui.user.UserActivity
import com.lazypotato.volleysampleapp.ui.user.album.photo.PhotoActivity

class AlbumRecyclerViewAdapter (
    private val context: Context,
    private val albums: List<Album>,
) :
    RecyclerView.Adapter<AlbumRecyclerViewAdapter.ViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_album_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.title.text = albums[position].title

        viewHolder.title.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, PhotoActivity::class.java).apply {
                putExtra("ALBUM_ID", albums[position].id)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            ContextCompat.startActivity(context, intent, null)
        })
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = albums.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
    }
}
