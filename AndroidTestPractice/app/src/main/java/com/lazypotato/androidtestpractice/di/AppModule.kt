package com.lazypotato.androidtestpractice.di

import android.content.Context
import androidx.room.Room
import com.lazypotato.androidtestpractice.data.local.ShoppingDao
import com.lazypotato.androidtestpractice.data.local.ShoppingItemDatabase
import com.lazypotato.androidtestpractice.data.remote.PixabayAPI
import com.lazypotato.androidtestpractice.other.Constants.BASE_URL
import com.lazypotato.androidtestpractice.other.Constants.DATABASE_NAME
import com.lazypotato.androidtestpractice.repositories.DefaultShoppingRepository
import com.lazypotato.androidtestpractice.repositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        dao: ShoppingDao,
         api: PixabayAPI
    ) = DefaultShoppingRepository(dao, api) as ShoppingRepository

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi(): PixabayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }
}

















