package com.lazypotato.mvvm_rxjava_retrofit_paging.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.repository.NetworkState
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.model.MovieDetails
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MovieDetailsViewModel(
    private val movieDetailsRepository: MovieDetailsRepository
): ViewModel() {
    private val  compositeDisposable = CompositeDisposable()

    fun getMovieDetails(movieId: Int): LiveData<MovieDetails>  {
        return movieDetailsRepository.fetchSingleMovieDetails(compositeDisposable, movieId)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieDetailsRepository.getMovieDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}