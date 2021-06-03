package com.lazypotato.volleysampleapp.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.lazypotato.volleysampleapp.data.constant.PreferenceConstants
import com.lazypotato.volleysampleapp.data.model.LocalUser
import java.util.*

class PrefManager private constructor(context: Context) {
    companion object {
        private const val TAG = "PREF MANAGER"

        private var pref: SharedPreferences? = null
        private var instance: PrefManager? = null

        @Synchronized
        fun getInstance(context: Context): PrefManager? {
            if (pref == null) instance = PrefManager(context)
            return instance
        }
    }

    init {
        pref = context.getSharedPreferences(TAG, Activity.MODE_PRIVATE)
    }

    val isLaunchedFirstTime: Boolean
        get() = pref!!.getBoolean(PreferenceConstants.FIRST_LAUNCH, true)

    fun setTheAppLaunchedFirstTime() {
        pref!!.edit().putBoolean(PreferenceConstants.FIRST_LAUNCH, false).apply()
    }

    val isLoggedIn: Boolean
        get() = false

    fun saveUserData(localUser: LocalUser?) {
//        String userInfo = new Gson().toJson(localUser);
//        pref.edit().putString(PreferenceConstants.USER_INFO, userInfo).apply();
//        saveAPIToken(localUser.getToken());
    }

    //        String userInfo = pref.getString(PreferenceConstants.USER_INFO, "");
//        LocalUser localUser = (userInfo.equals(""))
//                ? new LocalUser()
//                : new Gson().fromJson(userInfo, LocalUser.class);
//        return localUser;
    val userData: LocalUser
        get() =//        String userInfo = pref.getString(PreferenceConstants.USER_INFO, "");
//        LocalUser localUser = (userInfo.equals(""))
//                ? new LocalUser()
//                : new Gson().fromJson(userInfo, LocalUser.class);
//        return localUser;
            LocalUser("Test")

    fun clearUserData() {
        val localUser = LocalUser("")
        saveUserData(localUser)
        saveAPIToken("")
    }

    fun saveAPIToken(token: String?) {
        pref!!.edit().putString(PreferenceConstants.API_TOKEN, token).apply()
    }

    val apiToken: String?
        get() = pref!!.getString(PreferenceConstants.API_TOKEN, "")

    val authHeader: Map<String, String>
        get() {
            return mapOf(
                "Content-Type" to "application/json",
                PreferenceConstants.API_TOKEN_AUTH to "Bearer $apiToken"
            )
        }
    val header: Map<String, String>
        get() {
            return mapOf(
                "Content-Type" to "application/json",
            )
        }
}