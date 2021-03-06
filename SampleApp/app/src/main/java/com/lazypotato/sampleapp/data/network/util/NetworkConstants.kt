package com.lazypotato.volleysampleapp.data.network.util

import com.lazypotato.volleysampleapp.BuildConfig
import com.lazypotato.volleysampleapp.data.constant.AppConstants

object NetworkConstants {
    //private static final String BASE_URL = BuildConfig.BASE_URL_RELEASE;
    private val BASE_URL =
        if (AppConstants.DEBUG) BuildConfig.BASE_URL_DEV else BuildConfig.BASE_URL_RELEASE
    private const val TOKEN = BuildConfig.TOKEN

    @JvmStatic
    fun postGET(): String {
        return "${BASE_URL}/posts"
    }

    @JvmStatic
    fun postPOST(): String {
        return "${BASE_URL}/posts"
    }

    @JvmStatic
    fun userGET(): String {
        return "${BASE_URL}/users"
    }

    @JvmStatic
    fun commentsGET(postId: Int): String {
        return "${BASE_URL}/comments?postId=$postId"
    }

    @JvmStatic
    fun userInfoGET(userId: Int): String {
        return "${BASE_URL}/users/$userId"
    }

    @JvmStatic
    fun albumsGET(userId: Int): String {
        return "${BASE_URL}/albums?userId=$userId"
    }

    @JvmStatic
    fun todosGET(userId: Int): String {
        return "${BASE_URL}/todos?userId=$userId"
    }

    @JvmStatic
    fun photosGET(albumId: Int): String {
        return "${BASE_URL}/photos?albumId=$albumId"
    }
}