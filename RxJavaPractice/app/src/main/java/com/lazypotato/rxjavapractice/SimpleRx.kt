package com.lazypotato.rxjavapractice

import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.util.concurrent.TimeUnit

object SimpleRx {

    var bag = CompositeDisposable()

    fun simpleBehaviorRelay() {
        println("~~~~~SIMPLE VALUES~~~~~")

        val someInfo = BehaviorRelay.createDefault("1")
        println("someInfo.value ${someInfo.value}")

        val plainString = someInfo.value
        println("plainString: ${someInfo.value}")

        someInfo.accept("2")
        println("someInfo.value ${someInfo.value}")

        someInfo.subscribe { newValue ->
            println("value has changed: $newValue")
        }

        someInfo.accept("3")
    }

    fun simpleBehaviorSubject() {
        val behaviorSubject = BehaviorSubject.createDefault(24)

        val disposable = behaviorSubject.subscribe ({ newValue ->
                println("behavior subscription: $newValue")
            }, { error ->
                println("error: ${error.localizedMessage}")
            }, {
                println("completed")
            },
        )

        behaviorSubject.onNext(34)
        behaviorSubject.onNext(48)
        behaviorSubject.onNext(48)

        // 1 onError
//        val someException = IllegalArgumentException("Some Fake Error")
//        behaviorSubject.onError(someException)
//        behaviorSubject.onNext(109)

        // 2 onComplete
        behaviorSubject.onComplete()
        behaviorSubject.onNext(110)
    }

    fun basicObservable() {
        val observable = Observable.create<String> { observer ->
            println("~~ Observable Logic being triggered ~~")

            MainScope().launch {
                delay(1000)

                observer.onNext("some value 23")
                observer.onComplete()

                delay(1000)
            }
        }

        val observer = observable.subscribe { someString ->
            println("another subscriber: $someString")
        }

        //observer.dispose()
    }

    fun creatingObservables() {
//        val observable = Observable.just(23)

//        val observable = Observable.interval(300, TimeUnit.MILLISECONDS)
//            .timeInterval(AndroidSchedulers.mainThread())

//        val userIds = arrayOf(1,2,3,4,5,6)
//        val observable = Observable.fromArray(*userIds)
    }
}