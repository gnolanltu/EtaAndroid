package com.riis.simple.etaandroid.view.interfaces

import com.riis.simple.etaandroid.model.Route

interface RouteView {
    fun showProgressDialog()
    fun loadRoutes(routeResultList: List<Route>)
    fun navigateToStops(companyId: Int, routeId: Long, direction: String, daysActive: String)
}