package com.lazypotato.androidtestpractice.repositories

import androidx.lifecycle.LiveData
import com.lazypotato.androidtestpractice.data.local.ShoppingItem
import com.lazypotato.androidtestpractice.data.remote.responses.ImageResponse
import com.lazypotato.androidtestpractice.other.Resource
import retrofit2.Response

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}