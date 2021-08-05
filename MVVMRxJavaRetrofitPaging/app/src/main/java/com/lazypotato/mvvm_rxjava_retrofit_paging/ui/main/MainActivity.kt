package com.lazypotato.mvvm_rxjava_retrofit_paging.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.lazypotato.mvvm_rxjava_retrofit_paging.R
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.api.TheMovieDBClient
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.repository.NetworkState
import com.lazypotato.mvvm_rxjava_retrofit_paging.databinding.ActivityMainBinding
import com.lazypotato.mvvm_rxjava_retrofit_paging.ui.movie_details.MovieDetailsActivity
import com.lazypotato.mvvm_rxjava_retrofit_paging.ui.movie_details.MovieDetailsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val apiService = TheMovieDBClient.getClient()

        val movieRepository = MoviePagedListRepository(apiService)

        val viewModel: MainActivityViewModel by viewModels {
            MainActivityViewModelFactory(movieRepository)
        }

        val movieAdapter = PopularMoviePagedListAdapter(this)

        val gridLayoutManager = GridLayoutManager(this, 3)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = movieAdapter.getItemViewType(position)
                return if (viewType == movieAdapter.MOVIE_VIEW_TYPE) 1
                else 3
            }
        }

        binding.rvMovieList.apply {
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        viewModel.moviePagedList.observe(this, Observer {
            movieAdapter.submitList(it)
        })

        viewModel.networkState.observe(this, Observer {
            binding.progressBarPopular.visibility =
                if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            binding.txtErrorPopular.visibility =
                if (viewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE

            if(!viewModel.listIsEmpty()) {
                movieAdapter.setNetworkState(it)
            }
        })
    }
}
