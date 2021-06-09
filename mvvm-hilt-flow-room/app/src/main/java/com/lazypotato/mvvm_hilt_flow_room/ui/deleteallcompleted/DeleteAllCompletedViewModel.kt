package com.lazypotato.mvvm_hilt_flow_room.ui.deleteallcompleted

import androidx.lifecycle.ViewModel
import com.lazypotato.mvvm_hilt_flow_room.data.TaskDao
import com.lazypotato.mvvm_hilt_flow_room.di.ApplicationScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteAllCompletedViewModel @Inject constructor(
    private val taskDao: TaskDao,
    @ApplicationScope private val applicationScope: CoroutineScope
) : ViewModel() {

    fun onConfirmClick() = applicationScope.launch {
        taskDao.deleteCompletedTasks()
    }
}