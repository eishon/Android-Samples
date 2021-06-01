package com.lazypotato.ndkpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lazypotato.ndkpractice.databinding.ActivityMainBinding
import com.lazypotato.ndkpractice.kotlin.Calculator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = Calculator.add(5,3).toString()
    }

    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}