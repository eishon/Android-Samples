package com.lazypotato.mvvm_rxjava_retrofit_paging.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.api.POST_PER_PAGE
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.api.TheMovieDBService
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.model.Movie
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.repository.MovieDataSource
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.repository.NetworkState
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MoviePagedListRepository(private val apiService: TheMovieDBService) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var movieDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList(compositeDisposable: CompositeDisposable): LiveData<PagedList<Movie>> {
        movieDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(movieDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<MovieDataSource, NetworkState>(
            movieDataSourceFactory.movieLiveDataSource,
            MovieDataSource::networkState,
        )
    }
}