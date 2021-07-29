package com.lazypotato.rxjavapractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lazypotato.rxjavapractice.rx.SimpleRx
import com.lazypotato.rxjavapractice.rx.Traits

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //SimpleRx.basicObservable()
        Traits.traitsMayBe()
    }
}