package com.lazypotato.mvvm_hilt_flow_room.util

import androidx.appcompat.widget.SearchView

inline fun SearchView.setOnQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText.orEmpty())

            return true
        }

    })
}