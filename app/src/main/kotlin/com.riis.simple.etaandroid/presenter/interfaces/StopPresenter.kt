package com.riis.simple.etaandroid.presenter.interfaces

interface StopPresenter {
    fun getStops(companyNumber: Int, routeId: String, direction: String, daysActive: String)
    fun onWebCallStart()
    fun onWebCallComplete(stopsList : List<String>)
}