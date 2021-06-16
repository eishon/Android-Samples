package com.lazypotato.volleysampleapp.data.network.util

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleySingleton private constructor(mCtx: Context) {
    private var mRequestQueue: RequestQueue?

    companion object {
        private var mInstance: VolleySingleton? = null

        /**
         * Singleton construct design pattern.
         *
         * @param context parent context
         * @return single instance of VolleySingleton
         */
        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): VolleySingleton? {
            if (mInstance == null) {
                mInstance = VolleySingleton(context)
            }
            return mInstance
        }
    }

    /**
     * Private constructor, only initialization from getInstance.
     *
     * @param context parent context
     */
    init {
        mRequestQueue = Volley.newRequestQueue(mCtx)
    }

    /**
     * Add new request depend on type like string, json object, json array request.
     *
     * @param req new request
     * @param <T> request type
    </T> */
    fun <T> addToRequestQueue(req: Request<T>?) {
        mRequestQueue!!.add(req)
    }
}