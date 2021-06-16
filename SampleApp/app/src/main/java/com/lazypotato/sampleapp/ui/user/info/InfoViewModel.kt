package com.lazypotato.volleysampleapp.ui.user.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lazypotato.volleysampleapp.data.network.user.model.User

class InfoViewModel : ViewModel() {
    var user = MutableLiveData<User>()
}