package com.lazypotato.rxjavapractice

import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.lang.IllegalArgumentException

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
}