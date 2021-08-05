package com.lazypotato.mvvm_rxjava_retrofit_paging.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.api.TheMovieDBService
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.model.Movie
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.repository.MovieDataSource
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MovieDataSourceFactory(
    private val apiService: TheMovieDBService,
    private val compositeDisposable: CompositeDisposable,
): DataSource.Factory<Int, Movie>() {

    val movieLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieDataSource(apiService, compositeDisposable)

        movieLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}