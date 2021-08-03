package com.lazypotato.rxjavapractice.database

import android.content.Context
import androidx.room.Room

class DB(context: Context) {

    private val db = Room
        .databaseBuilder(
            context,
            AppDatabase::class.java,
            "rxjava-practice"
        )
        .fallbackToDestructiveMigration()
        .build()

    val postDao = db.postDao()
}