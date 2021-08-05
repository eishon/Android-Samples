package com.lazypotato.mvvm_rxjava_retrofit_paging.data.api

import com.lazypotato.mvvm_rxjava_retrofit_paging.data.model.MovieDetails
import com.lazypotato.mvvm_rxjava_retrofit_paging.data.model.MoviesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBService {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Single<MoviesResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>
}