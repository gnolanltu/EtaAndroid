package com.riis.simple.etaandroid.controllers

import com.riis.simple.etaandroid.callback.GetRoutesCallback
import com.riis.simple.etaandroid.model.Route
import com.riis.simple.etaandroid.model.RouteModel
import com.riis.simple.etaandroid.view.RouteActivity

class RoutesController(val activity: RouteActivity) : GetRoutesCallback {
    fun getRoutes(companyNumber: Int) {
        val model = RouteModel(this)
        model.getCompanyRoutes(companyNumber)
    }

    override fun onStart() {
        activity.showProgressDialog()
    }

    override fun onComplete(routeList: List<Route>) {
        activity.loadRoutes(routeList)
    }
}
