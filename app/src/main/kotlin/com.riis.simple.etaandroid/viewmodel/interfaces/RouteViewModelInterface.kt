package com.riis.simple.etaandroid.presenter.interfaces

import com.riis.simple.etaandroid.model.Route

interface RoutePresenter {
    fun onWebCallStart()
    fun onWebCallComplete(routeList : List<Route>)
}