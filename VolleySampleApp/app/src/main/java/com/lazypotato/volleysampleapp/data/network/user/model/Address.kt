package com.lazypotato.volleysampleapp.data.network.user.model

import java.io.Serializable

data class Address(
    var street: String,
    var suite: String,
    var city: String,
    var zipcode: String,
    var geo: Geo,
): Serializable