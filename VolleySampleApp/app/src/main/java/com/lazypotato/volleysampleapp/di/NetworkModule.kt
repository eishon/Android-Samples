package com.lazypotato.volleysampleapp.di

import android.content.Context
import com.lazypotato.volleysampleapp.data.network.post.get.GETPosts
import com.lazypotato.volleysampleapp.data.network.util.ErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ActivityComponent::class)
class NetworkModule {

    @Provides
    fun providesGETPosts(@ApplicationContext context: Context, errorHandler: ErrorHandler) = GETPosts(context, errorHandler)

}