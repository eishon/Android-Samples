package com.lazypotato.architechture_components_practice.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashResponse(
    val results: List<UnsplashPhoto>
): Parcelable