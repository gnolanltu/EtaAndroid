package com.riis.simple.etaandroid.controllers.route

import com.riis.simple.etaandroid.callback.GetRoutesCallback
import com.riis.simple.etaandroid.model.Route
import com.riis.simple.etaandroid.view.RouteActivity

class RoutesController(val activity: RouteActivity) : GetRoutesCallback {
    fun getRoutes(companyNumber: Int) {
        GetRoutesAsyncTask(this).execute(companyNumber)
    }

    override fun onStart() {
        activity.showProgressDialog()
    }

    override fun onComplete(routeList: List<Route>) {
        activity.loadRoutes(routeList)
    }
}
