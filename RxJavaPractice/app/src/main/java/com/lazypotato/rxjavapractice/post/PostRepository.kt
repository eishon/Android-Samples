package com.lazypotato.rxjavapractice.post

import com.lazypotato.rxjavapractice.database.PostDao
import com.lazypotato.rxjavapractice.model.Post
import com.lazypotato.rxjavapractice.network.API
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class PostRepository(
    private val service: API,
    private val dao: PostDao,
) {

    fun fetchPostFromDatabase(): Single<List<Post>>{
        return dao.getAll()
    }

    fun fetchPostFromAPI(): Single<List<Post>> {
        return Single.create { observer ->

            service.getPosts().enqueue(object: Callback<List<Post>> {
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    val postList = response?.body()

                    if(postList != null) {
                        observer.onSuccess(postList)
                    } else {
                        val e = IOException("An Unknown Network Error Occurred")
                        observer.onError(e)
                    }
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    val e = t ?: IOException("An Unknown Network Error Occurred")
                    observer.onError(e)
                }

            })
        }
    }
}