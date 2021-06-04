package com.lazypotato.volleysampleapp.data.network.todos.get

import com.lazypotato.volleysampleapp.base.api.ProgressListener
import com.lazypotato.volleysampleapp.data.network.todos.model.ToDo

interface ToDoResponseListener: ProgressListener {
    fun onToDoResponse(todos: List<ToDo>, responseCode: Int)
}