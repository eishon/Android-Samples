package com.lazypotato.volleysampleapp.di

import android.content.Context
import com.lazypotato.volleysampleapp.data.network.util.ErrorHandler
import com.lazypotato.volleysampleapp.util.PrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePrefManager(@ApplicationContext context: Context) = PrefManager(context)

    @Provides
    @Singleton
    fun providesErrorHandler(prefManager: PrefManager) = ErrorHandler(prefManager)
}