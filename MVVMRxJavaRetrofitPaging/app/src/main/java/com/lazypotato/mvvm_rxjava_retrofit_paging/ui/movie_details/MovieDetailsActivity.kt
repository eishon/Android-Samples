package com.lazypotato.mvvm_rxjava_retrofit_paging.ui.movie_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.lazypotato.mvvm_rxjava_retrofit_paging.R
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.api.POSTER_BASE_URL
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.api.TheMovieDBClient
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.api.TheMovieDBService
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.model.MovieDetails
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.repository.NetworkState
import com.lazypotato.mvvm_rxjava_retrofit_paging.databinding.ActivityMovieDetailsBinding
import java.text.NumberFormat
import java.util.*

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    private val viewModel: MovieDetailsViewModel by viewModels { MovieDetailsViewModelFactory(movieDetailsRepository)}
    private lateinit var movieDetailsRepository: MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        
        val movieId = intent.getIntExtra("id",1)
        
        val apiService: TheMovieDBService = TheMovieDBClient.getClient()
        movieDetailsRepository = MovieDetailsRepository(apiService)
        
        viewModel.getMovieDetails(movieId).observe(this, Observer { 
            bindUI(it)
        })

        viewModel.networkState.observe(this, Observer {
            binding.progressBar.visibility = if(it == NetworkState.LOADING) View.VISIBLE else View.GONE
            binding.txtError.visibility = if(it == NetworkState.ERROR) View.VISIBLE else View.GONE
        })
    }

    private fun bindUI(movieDetails: MovieDetails) {
        binding.movieTitle.text = movieDetails.title
        binding.movieTagline.text = movieDetails.tagline
        binding.movieReleaseDate.text = movieDetails.releaseDate
        //movie_rating.text = movieDetails.rating
        binding.movieRuntime.text = movieDetails.runtime.toString()
        binding.movieOverview.text = movieDetails.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        binding.movieBudget.text = formatCurrency.format(movieDetails.budget)
        binding.movieRevenue.text = formatCurrency.format(movieDetails.budget)

        val moviePosterUrl = POSTER_BASE_URL + movieDetails.posterPath
        Glide.with(this)
            .load(moviePosterUrl)
            .into(binding.ivMoviePoster)

    }
}