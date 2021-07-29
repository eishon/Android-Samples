package com.lazypotato.rxjavapractice

import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.disposables.CompositeDisposable

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
}