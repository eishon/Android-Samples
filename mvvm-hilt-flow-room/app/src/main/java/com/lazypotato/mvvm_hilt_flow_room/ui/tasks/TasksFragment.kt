package com.lazypotato.mvvm_hilt_flow_room.ui.tasks

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lazypotato.mvvm_hilt_flow_room.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment: Fragment(R.layout.fragment_tasks) {
    private val viewModel: TasksViewModel by viewModels()
}