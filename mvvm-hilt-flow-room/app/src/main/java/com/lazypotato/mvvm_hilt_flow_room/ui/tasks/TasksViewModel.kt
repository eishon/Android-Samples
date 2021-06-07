package com.lazypotato.mvvm_hilt_flow_room.ui.tasks

import androidx.lifecycle.ViewModel
import com.lazypotato.mvvm_hilt_flow_room.data.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskDao: TaskDao
): ViewModel() {
}