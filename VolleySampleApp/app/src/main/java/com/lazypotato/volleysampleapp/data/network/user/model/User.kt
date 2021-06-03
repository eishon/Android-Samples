package com.lazypotato.volleysampleapp.data.network.user.model

import java.io.Serializable

data class User(
    var id: Int,
    var name: String,
    var username: String,
    var email: String,
    var phone: String,
    var website: String,
    var address: Address,
    var company: Company,

    ) : Serializable