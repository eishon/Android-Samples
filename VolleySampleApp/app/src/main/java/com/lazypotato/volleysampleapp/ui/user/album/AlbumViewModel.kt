package com.lazypotato.volleysampleapp.ui.user.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lazypotato.volleysampleapp.data.network.albums.model.Album

class AlbumViewModel : ViewModel() {
    var albumList = MutableLiveData<List<Album>>()
}