package com.riis.simple.etaandroid.viewmodel.interfaces

interface StopViewModelInterface {
    fun onWebCallStart()
    fun onWebCallComplete(stopsList: List<String>)
}