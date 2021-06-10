package com.lazypotato.volleysampleapp.di

import android.content.Context
import com.lazypotato.volleysampleapp.data.network.albums.get.GETAlbums
import com.lazypotato.volleysampleapp.data.network.comment.get.GETComments
import com.lazypotato.volleysampleapp.data.network.photo.get.GETPhotos
import com.lazypotato.volleysampleapp.data.network.post.get.GETPosts
import com.lazypotato.volleysampleapp.data.network.todos.get.GETToDos
import com.lazypotato.volleysampleapp.data.network.user.get.GETUserInfo
import com.lazypotato.volleysampleapp.data.network.user.get.GETUsers
import com.lazypotato.volleysampleapp.data.network.util.ErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
class NetworkModule {

    @Provides
    fun providesGETPosts(@ApplicationContext context: Context, errorHandler: ErrorHandler) = GETPosts(context, errorHandler)

    @Provides
    fun providesGETComments(@ApplicationContext context: Context, errorHandler: ErrorHandler) = GETComments(context, errorHandler)

    @Provides
    fun providesGETUsers(@ApplicationContext context: Context, errorHandler: ErrorHandler) = GETUsers(context, errorHandler)

    @Provides
    fun providesGETUserInfo(@ApplicationContext context: Context, errorHandler: ErrorHandler) = GETUserInfo(context, errorHandler)

    @Provides
    fun providesGETAlbums(@ApplicationContext context: Context, errorHandler: ErrorHandler) = GETAlbums(context, errorHandler)

    @Provides
    fun providesGETPhotos(@ApplicationContext context: Context, errorHandler: ErrorHandler) = GETPhotos(context, errorHandler)

    @Provides
    fun providesGETToDos(@ApplicationContext context: Context, errorHandler: ErrorHandler) = GETToDos(context, errorHandler)

}