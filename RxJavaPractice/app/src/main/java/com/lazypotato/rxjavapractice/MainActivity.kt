package com.lazypotato.rxjavapractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.lazypotato.rxjavapractice.database.DB
import com.lazypotato.rxjavapractice.databinding.ActivityMainBinding
import com.lazypotato.rxjavapractice.network.RetrofitService.service
import com.lazypotato.rxjavapractice.model.Post
import com.lazypotato.rxjavapractice.post.PostActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var bag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //SimpleRx.basicObservable()
        //Traits.traitsMayBe()

        realSingleExample()

        binding.button.setOnClickListener {
            startActivity(Intent(applicationContext, PostActivity::class.java))
        }
    }

    //region Rx Code
    private fun realSingleExample() {
        loadPostAsSingle()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ posting ->
                binding.userId.text = posting.title
                binding.bodyText.text = posting.body
            }, { error ->
                println("An Error Occurred: ${error.localizedMessage}")
                binding.userId.text = ""
                binding.bodyText.text = ""
            })
    }

    private fun loadPostAsSingle(): Single<Post> {
        return Single.create { observer ->
            Thread.sleep(2000)
            
            val postingId = 5
            service.getPostDetails(postingId.toString())
                .enqueue(object : Callback<Post> {
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        val posting = response?.body()

                        if(posting != null) {
                            observer.onSuccess(posting)
                        } else {
                            val e = IOException("An Unknown Network Error Occurred")
                            observer.onError(e)
                        }
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {
                        val e = t ?: IOException("An Unknown Network Error Occurred")
                        observer.onError(e)
                    }

                })
        }
    }
    //endregion

    override fun onDestroy() {
        super.onDestroy()
        bag.clear()
    }
}