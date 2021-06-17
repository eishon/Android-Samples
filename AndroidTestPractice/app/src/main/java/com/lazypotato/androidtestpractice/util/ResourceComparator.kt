package com.lazypotato.androidtestpractice.util

import android.content.Context

class ResourceComparator {
    fun isEqual(context: Context, resId: Int, string: String): Boolean {
        return context.getString(resId) == string
    }
}