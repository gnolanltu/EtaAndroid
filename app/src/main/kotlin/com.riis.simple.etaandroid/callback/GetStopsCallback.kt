package com.riis.simple.etaandroid.callback

interface GetStopsCallback {
    fun onStart()
    fun onComplete(stopsList: List<String>)
}