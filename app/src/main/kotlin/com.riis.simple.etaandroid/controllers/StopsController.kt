package com.riis.simple.etaandroid.controllers

import com.riis.simple.etaandroid.callback.GetStopsCallback
import com.riis.simple.etaandroid.model.StopModel
import com.riis.simple.etaandroid.view.StopActivity

class StopsController(val activity: StopActivity) : GetStopsCallback {
    fun getStops(companyNumber: Int, routeId: String, direction: String, daysActive: String) {
        val model = StopModel(this)
        model.getStops(companyNumber, routeId, direction, daysActive)
    }

    override fun onStart() {
        activity.showProgressDialog()
    }

    override fun onComplete(stopsList: List<String>) {
        activity.loadStops(stopsList)
    }
}