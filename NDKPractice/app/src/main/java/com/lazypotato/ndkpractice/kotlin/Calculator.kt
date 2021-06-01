package com.lazypotato.ndkpractice.kotlin

class Calculator {
    companion object {
        external fun add(n1: Long, n2: Long): Long

        external fun subtract(n1: Long, n2: Long): Long
    }
}