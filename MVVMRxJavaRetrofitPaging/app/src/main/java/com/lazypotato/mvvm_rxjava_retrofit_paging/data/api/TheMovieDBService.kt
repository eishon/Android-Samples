package com.lazypotato.mvvm_rxjava_retrofit_paging.data.api

import com.lazypotato.mvvm_rxjava_retrofit_paging.data.model.MovieDetails
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDBService {

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>


}