package com.lazypotato.volleysampleapp.ui.user.todos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lazypotato.volleysampleapp.data.network.todos.model.ToDo

class ToDosViewModel : ViewModel() {
    var todoList = MutableLiveData<List<ToDo>>()
}