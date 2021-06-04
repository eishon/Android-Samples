package com.lazypotato.volleysampleapp.data.network.todos.model

import java.io.Serializable

data class ToDo(
    var id: Int,
    var userId: Int,
    var title: String,
    var completed: Boolean
    ): Serializable
