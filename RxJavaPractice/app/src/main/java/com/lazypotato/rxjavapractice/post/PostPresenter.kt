package com.lazypotato.rxjavapractice.post

import com.lazypotato.rxjavapractice.database.DB
import com.lazypotato.rxjavapractice.database.PostDao
import com.lazypotato.rxjavapractice.model.Post
import com.lazypotato.rxjavapractice.network.API
import io.reactivex.rxjava3.core.Single

class PostPresenter(
    service: API,
    dao: PostDao,
) {
    private val repository = PostRepository(service, dao)

    fun loadPosts(): Single<List<Post>> {
        //return repository.fetchPostFromAPI()
        //return repository.fetchPostFromDatabase()

        var apiPosts = repository.fetchPostFromAPI()
            .map { posts ->
                posts.filter { post ->
                    post.id % 2 == 0
                }
            }

        var dbPosts = repository.fetchPostFromDatabase()

        return Single.zip(apiPosts, dbPosts, { apiPosts, dbPosts ->
            return@zip apiPosts + dbPosts
        })
    }

}