package com.lazypotato.volleysampleapp.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.lazypotato.volleysampleapp.data.constant.PreferenceConstants
import com.lazypotato.volleysampleapp.data.model.LocalUser
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "PREF MANAGER"

@Singleton
class PrefManager @Inject constructor(context: Context) {
    private var pref: SharedPreferences = context.getSharedPreferences(TAG, Activity.MODE_PRIVATE)

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