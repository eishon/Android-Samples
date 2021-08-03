package com.lazypotato.rxjavapractice.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lazypotato.rxjavapractice.model.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}