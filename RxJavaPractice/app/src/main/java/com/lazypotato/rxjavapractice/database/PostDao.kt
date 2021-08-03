package com.lazypotato.rxjavapractice.database

import androidx.room.*
import com.lazypotato.rxjavapractice.model.Post
import io.reactivex.rxjava3.core.Single

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getAll(): Single<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg posts: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: Post)

    @Delete
    fun delete(post: Post)
}