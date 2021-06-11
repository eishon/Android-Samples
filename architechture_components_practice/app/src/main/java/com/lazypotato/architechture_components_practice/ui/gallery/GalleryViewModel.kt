package com.lazypotato.architechture_components_practice.ui.gallery

import androidx.lifecycle.ViewModel
import com.lazypotato.architechture_components_practice.data.UnsplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repository: UnsplashRepository
): ViewModel() {

}