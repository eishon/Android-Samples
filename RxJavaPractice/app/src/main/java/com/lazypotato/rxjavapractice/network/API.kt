package com.lazypotato.rxjavapractice.network

import com.lazypotato.rxjavapractice.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts/{id}")
    fun getPostDetails(@Path("id") id: String): Call<Post>
}