package com.lazypotato.volleysampleapp.ui.user.album.photo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lazypotato.volleysampleapp.R
import com.lazypotato.volleysampleapp.data.network.comment.model.Comment
import com.lazypotato.volleysampleapp.data.network.photo.model.Photo
import java.util.*

class PhotoRecyclerViewAdapter (
    private val context: Context,
    private val photos: List<Photo>,
) :
    RecyclerView.Adapter<PhotoRecyclerViewAdapter.ViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_photo_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.title.text = photos[position].title

        var imageUrl = "https://picsum.photos/640/640?random=${Random().nextInt(100)}"
        Glide
            .with(context)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(viewHolder.imageView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = photos.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }
}
