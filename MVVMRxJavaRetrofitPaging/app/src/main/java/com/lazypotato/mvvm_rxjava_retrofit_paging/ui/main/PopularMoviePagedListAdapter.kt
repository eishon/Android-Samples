package com.lazypotato.mvvm_rxjava_retrofit_paging.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lazypotato.mvvm_rxjava_retrofit_paging.R
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.api.POSTER_BASE_URL
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.model.Movie
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.repository.NetworkState
import com.lazypotato.mvvm_rxjava_retrofit_paging.ui.movie_details.MovieDetailsActivity

class PopularMoviePagedListAdapter(
    private val context: Context
) : PagedListAdapter<Movie, RecyclerView.ViewHolder>(MovieDiffCallback()) {

    val MOVIE_VIEW_TYPE = 1
    val NETWORK_VIEW_TYPE = 2

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View

        if(viewType == MOVIE_VIEW_TYPE) {
            view = layoutInflater.inflate(R.layout.movie_list_item, parent, false)
            return MovieItemViewHolder(view)
        } else {
            view = layoutInflater.inflate(R.layout.network_state_item, parent, false)
            return NetworksStateItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == MOVIE_VIEW_TYPE) {
            (holder as MovieItemViewHolder).bind(getItem(position), context)
        } else {
            (holder as NetworksStateItemViewHolder).bind(networkState)
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if(hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            NETWORK_VIEW_TYPE
        } else {
            MOVIE_VIEW_TYPE
        }
    }

    fun setNetworkState(networkState: NetworkState) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()

        this.networkState = networkState
        val hasExtraRow = hasExtraRow()

        if(hadExtraRow != hasExtraRow) {
            if(hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != networkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    class MovieItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie?, context: Context) {
            itemView.findViewById<TextView>(R.id.cv_movie_title).text = movie?.title
            itemView.findViewById<TextView>(R.id.cv_movie_release_date).text = movie?.releaseDate

            val moviePosterUrl = POSTER_BASE_URL + movie?.posterPath
            Glide.with(itemView.context)
                .load(moviePosterUrl)
                .into(itemView.findViewById<ImageView>(R.id.cv_iv_movie_poster))

            itemView.setOnClickListener {
                val intent = Intent(context, MovieDetailsActivity::class.java)
                intent.putExtra("id", movie?.id)
                context.startActivity(intent)
            }
        }
    }

    class NetworksStateItemViewHolder(view: View): RecyclerView.ViewHolder(view)  {
        fun bind(networkState: NetworkState?) {
            when (networkState) {
                null -> {
                    itemView.findViewById<ProgressBar>(R.id.progress_bar_item).visibility = View.GONE
                    itemView.findViewById<TextView>(R.id.error_msg_item).visibility = View.GONE
                }
                NetworkState.LOADING -> {
                    itemView.findViewById<ProgressBar>(R.id.progress_bar_item).visibility = View.VISIBLE
                    itemView.findViewById<TextView>(R.id.error_msg_item).visibility = View.GONE
                }
                NetworkState.ERROR -> {
                    itemView.findViewById<ProgressBar>(R.id.progress_bar_item).visibility = View.GONE
                    itemView.findViewById<TextView>(R.id.error_msg_item).visibility = View.VISIBLE
                    itemView.findViewById<TextView>(R.id.error_msg_item).text = networkState.msg
                }
                NetworkState.END_OF_LIST -> {
                    itemView.findViewById<ProgressBar>(R.id.progress_bar_item).visibility = View.GONE
                    itemView.findViewById<TextView>(R.id.error_msg_item).visibility = View.VISIBLE
                    itemView.findViewById<TextView>(R.id.error_msg_item).text = networkState.msg
                }
            }
        }
    }

    class MovieDiffCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
}