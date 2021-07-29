package com.lazypotato.rxjavapractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.lazypotato.rxjavapractice.databinding.ActivityMainBinding
import com.lazypotato.rxjavapractice.rx.SimpleRx
import com.lazypotato.rxjavapractice.rx.Traits
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var bag = CompositeDisposable()

    // region Simple Network Layer

    interface JsonPlaceHolderService {
        @GET("posts/{id}")
        fun getPosts(@Path("id") id: String): Call<Posting>
    }

    private var retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .build()

    private var service = retrofit.create(JsonPlaceHolderService::class.java)

    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //SimpleRx.basicObservable()
        //Traits.traitsMayBe()

        realSingleExample()
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

    private fun loadPostAsSingle(): Single<Posting> {
        return Single.create { observer ->
            Thread.sleep(2000)
            
            val postingId = 5
            service.getPosts(postingId.toString())
                .enqueue(object : Callback<Posting> {
                    override fun onResponse(call: Call<Posting>, response: Response<Posting>) {
                        val posting = response?.body()

                        if(posting != null) {
                            observer.onSuccess(posting)
                        } else {
                            val e = IOException("An Unknown Network Error Occurred")
                            observer.onError(e)
                        }
                    }

                    override fun onFailure(call: Call<Posting>, t: Throwable) {
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