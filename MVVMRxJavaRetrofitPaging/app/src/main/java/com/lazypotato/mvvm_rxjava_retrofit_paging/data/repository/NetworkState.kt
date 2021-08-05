package com.lazypotato.mvvm_rxjava_retrofit_paging.data.repository

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED,
    END_OF_LIST
}

class NetworkState(val status: Status, val msg: String) {
    companion object {
        val LOADED: NetworkState = NetworkState(Status.SUCCESS, "Success")
        val LOADING: NetworkState = NetworkState(Status.RUNNING, "Running")
        val ERROR: NetworkState = NetworkState(Status.FAILED, "Something went wrong")
        val END_OF_LIST: NetworkState = NetworkState(Status.END_OF_LIST, "You have reached the end")
    }
}