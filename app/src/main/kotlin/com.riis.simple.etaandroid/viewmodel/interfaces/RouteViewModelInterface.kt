package com.riis.simple.etaandroid.viewmodel.interfaces

import com.riis.simple.etaandroid.model.Route

interface RouteViewModelInterface {
    fun onWebCallStart()
    fun onWebCallComplete(routeList : List<Route>)
}