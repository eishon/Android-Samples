package com.lazypotato.rxjavapractice.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import java.lang.IllegalArgumentException

object Traits {
    fun traitsSingle () {
        val single = Single.create<String> { single ->
            val success = true

            if(success) {
                single.onSuccess("nice work!")
            } else {
                val someException = IllegalArgumentException("some fake error")
                single.onError(someException)
            }
        }

        single.subscribe({ result ->
            println("single: $result")
        }, { error ->

        })
    }

    fun traitsCompletable () {
        val completable = Completable.create { completable ->
            val success = true

            if(success) {
                completable.onComplete()
            } else {
                val someException = IllegalArgumentException("some fake error")
                completable.onError(someException)
            }
        }

        completable.subscribe({
            println("completable completed")
        }, { error ->

        })
    }

    fun traitsMayBe () {
        val maybe = Maybe.create<String> { maybe ->
            val success = true
            val hasResult = true

            if(success) {
                if (hasResult) {
                    maybe.onSuccess("some result")
                } else {
                    maybe.onComplete()
                }
            } else {
                val someException = IllegalArgumentException("some fake error")
                maybe.onError(someException)
            }
        }

        maybe .subscribe({ result ->
            println("May Be - Result: $result")
        }, { error ->

        }, {
            println("May Be - Completed")
        })
    }
}