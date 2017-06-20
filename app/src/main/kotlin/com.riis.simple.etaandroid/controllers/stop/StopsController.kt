package com.riis.simple.etaandroid.controllers.stop

import com.riis.simple.etaandroid.callback.GetStopsCallback
import com.riis.simple.etaandroid.view.StopActivity

class StopsController(val activity: StopActivity) : GetStopsCallback {
    fun getStops(companyNumber: Int, routeId: String, direction: String, daysActive: String) {
        GetStopsAsyncTask(companyNumber, this).execute(routeId, direction, daysActive)
    }

    override fun onStart() {
        activity.showProgressDialog()
    }

    override fun onComplete(stopsList: List<String>) {
        activity.loadStops(stopsList)
    }
}