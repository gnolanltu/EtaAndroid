package com.riis.simple.etaandroid.callback

import com.riis.simple.etaandroid.model.Route

interface GetRoutesCallback {
    fun onStart()
    fun onComplete(routeList : List<Route>)
}