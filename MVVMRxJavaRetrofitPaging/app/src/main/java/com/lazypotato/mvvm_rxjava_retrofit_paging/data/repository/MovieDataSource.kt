package com.lazypotato.mvvm_rxjava_retrofit_paging.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.api.FIRST_PAGE
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.api.TheMovieDBService
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.model.Movie
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDataSource(
    private val apiService: TheMovieDBService,
    private val compositeDisposable: CompositeDisposable,
) : PageKeyedDataSource<Int, Movie>() {

    private var page = FIRST_PAGE

    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiService.getPopularMovies(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        if(it.totalPages >= params.key) {
                            callback.onResult(it.movies, params.key+1)
                            networkState.postValue(NetworkState.LOADED)
                        } else {
                            networkState.postValue(NetworkState.END_OF_LIST)
                        }
                    }, {
                        networkState.postValue(NetworkState.ERROR)
                        it.message?.let { it1 -> Log.e("MovieDataSource", it1) }
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        TODO("Not yet implemented")
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiService.getPopularMovies(page)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(it.movies, null, page+1)
                        networkState.postValue(NetworkState.LOADED)
                    }, {
                        networkState.postValue(NetworkState.ERROR)
                        it.message?.let { it1 -> Log.e("MovieDataSource", it1) }
                    }
                )
        )
    }
}