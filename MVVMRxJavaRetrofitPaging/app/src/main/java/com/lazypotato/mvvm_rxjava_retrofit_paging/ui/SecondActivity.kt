package com.lazypotato.mvvm_rxjava_retrofit_paging.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.lazypotato.mvvm_rxjava_retrofit_paging.R
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.api.TheMovieDBClient
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.api.TheMovieDBService
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.model.MovieDetails
import com.lazypotato.mvvm_rxjava_retrofit_paging.ui.movie_details.MovieDetailsRepository
import com.lazypotato.mvvm_rxjava_retrofit_paging.ui.movie_details.MovieDetailsViewModel
import com.lazypotato.mvvm_rxjava_retrofit_paging.ui.movie_details.MovieDetailsViewModelFactory

class SecondActivity : AppCompatActivity() {

    private val viewModel: MovieDetailsViewModel by viewModels { MovieDetailsViewModelFactory(movieDetailsRepository)}
    private lateinit var movieDetailsRepository: MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        
        val movieId: Int = 1
        
        val apiService: TheMovieDBService = TheMovieDBClient.getClient()
        movieDetailsRepository = MovieDetailsRepository(apiService)
        
        viewModel.getMovieDetails(movieId).observe(this, Observer { 
            bindUI(it)
        })
    }

    private fun bindUI(movieDetails: MovieDetails) {

    }


}