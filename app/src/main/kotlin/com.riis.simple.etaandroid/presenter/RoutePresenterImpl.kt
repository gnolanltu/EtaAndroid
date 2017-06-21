package com.riis.simple.etaandroid.presenter

import com.riis.simple.etaandroid.model.Route
import com.riis.simple.etaandroid.model.RouteModel
import com.riis.simple.etaandroid.presenter.interfaces.RoutePresenter
import com.riis.simple.etaandroid.view.interfaces.RouteView

class RoutePresenterImpl(val view: RouteView) : RoutePresenter {
    override fun getRoutes(companyNumber: Int) {
        val model = RouteModel(this)
        model.getCompanyRoutes(companyNumber)
    }

    override fun onWebCallStart() {
        view.showProgressDialog()
    }

    override fun onWebCallComplete(routeList: List<Route>) {
        view.loadRoutes(routeList)
    }
}