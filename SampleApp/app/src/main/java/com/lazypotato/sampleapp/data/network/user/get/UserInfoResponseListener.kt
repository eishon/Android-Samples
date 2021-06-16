package com.lazypotato.volleysampleapp.data.network.user.get

import com.lazypotato.volleysampleapp.base.api.ProgressListener
import com.lazypotato.volleysampleapp.data.network.user.model.User

interface UserInfoResponseListener: ProgressListener {
    fun onUserInfoResponse(user: User?, responseCode: Int)
}