package com.lazypotato.mvvm_hilt_flow_room.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lazypotato.mvvm_hilt_flow_room.data.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskDao: TaskDao
): ViewModel() {

    val searchQuery = MutableStateFlow("")

    val sortOrder = MutableStateFlow(SortOrder.BY_DATE)
    val hideCompleted = MutableStateFlow(false)

    private val tasksFlow = combine(
        searchQuery,
        sortOrder,
        hideCompleted
    ) { query, sortOrder, hideCompleted ->
        Triple(query, sortOrder, hideCompleted)
    }.flatMapLatest { (query, sortOrder, hideCompleted) ->
        taskDao.getTasks(query, sortOrder, hideCompleted)
    }

    val tasks = tasksFlow.asLiveData()
}

enum class SortOrder {
    BY_NAME,
    BY_DATE
}