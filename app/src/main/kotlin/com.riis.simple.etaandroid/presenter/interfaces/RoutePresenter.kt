package com.riis.simple.etaandroid.presenter.interfaces

import com.riis.simple.etaandroid.model.Route

interface RoutePresenter {
    fun getRoutes(companyNumber: Int)
    fun onWebCallStart()
    fun onWebCallComplete(routeList : List<Route>)
    fun onRouteRowClicked(route: Route)
}