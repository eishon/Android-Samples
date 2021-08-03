package com.lazypotato.rxjavapractice.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.lazypotato.rxjavapractice.R
import com.lazypotato.rxjavapractice.database.DB
import com.lazypotato.rxjavapractice.databinding.ActivityPostBinding
import com.lazypotato.rxjavapractice.network.API
import com.lazypotato.rxjavapractice.network.RetrofitService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.runBlocking

class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding

    private val adapter = PostListAdapter()

    private val bag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post)

        val db = DB(applicationContext)

        val presenter = PostPresenter(RetrofitService.service, db.postDao)

        binding.apply {
            postRecyclerView.adapter = adapter
            postRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        }

        val postListObservable = presenter.loadPosts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ posts ->
                adapter.setData(posts)
            }, { error ->
                println("An Error Occurred: ${error.localizedMessage}")
            })

        bag.add(postListObservable)
    }

    override fun onDestroy() {
        super.onDestroy()

        bag.dispose()
    }
}