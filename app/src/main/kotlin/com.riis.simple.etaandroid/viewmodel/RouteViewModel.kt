package com.riis.simple.etaandroid.viewmodel

import android.content.Context
import android.content.Intent
import com.riis.simple.etaandroid.model.Route
import com.riis.simple.etaandroid.view.StopActivity
import java.util.*

class RouteViewModel(val context: Context, val route: Route) : Observable() {
    var routeDisplayText: String = route.routeId

    fun onItemSelected() {
        val stopIntent = Intent(context, StopActivity::class.java)
        stopIntent.putExtra(StopActivity.EXTRA_COMPANY, route.companyId)
        stopIntent.putExtra(StopActivity.EXTRA_ROUTEID, route.id)
        stopIntent.putExtra(StopActivity.EXTRA_DIRECTION, route.direction1)
        stopIntent.putExtra(StopActivity.EXTRA_DAYSACTIVE, route.daysActive)
        context.startActivity(stopIntent)
    }
}