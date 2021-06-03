package com.lazypotato.volleysampleapp.data.network.user

import com.lazypotato.volleysampleapp.base.api.ProgressListener
import com.lazypotato.volleysampleapp.data.network.user.model.User

interface UserResponseListener: ProgressListener {
    fun onUsersResponse(users: List<User>, responseCode: Int)
}