package com.riis.simple.etaandroid.viewmodel

import android.content.Intent
import com.riis.simple.etaandroid.presenter.StopPresenterImpl
import com.riis.simple.etaandroid.presenter.interfaces.StopPresenter
import com.riis.simple.etaandroid.view.StopActivity
import com.riis.simple.etaandroid.view.interfaces.StopView
import java.util.*

class StopActivityViewModel(intent: Intent, view: StopView) : Observable() {
    private var checked : Boolean = true

    private var companyNumber: Int = 0
    private var routeId: String = ""
    private var direction1: String = ""
    private var direction2: String = ""
    private var daysActive: String = ""

    private var stopPresenter: StopPresenter = StopPresenterImpl(view)

    init {
        companyNumber = intent.getIntExtra(StopActivity.EXTRA_COMPANY, -1)
        routeId = intent.getLongExtra(StopActivity.EXTRA_ROUTEID, -1).toString()
        direction1 = intent.getStringExtra(StopActivity.EXTRA_DIRECTION)
        daysActive = intent.getStringExtra(StopActivity.EXTRA_DAYSACTIVE)

        //if daysActive has more than one value, grab first value
        val commaIndex = daysActive.indexOf(",")
        if (commaIndex != -1) {
            daysActive = daysActive.substring(0, commaIndex)
        }

        //Always show North or Eastbound first for consistency
        when (direction1.toLowerCase()) {
            "northbound" -> {
                direction1 = "Northbound"
                direction2 = "Southbound"
            }
            "southbound" -> {
                direction1 = "Northbound"
                direction2 = "Southbound"
            }
            "eastbound" -> {
                direction1 = "Eastbound"
                direction2 = "Westbound"
            }
            "westbound" -> {
                direction1 = "Eastbound"
                direction2 = "Westbound"
            }
            else -> {
            }
        }
    }

    fun onCheckChanged() {
        checked = !checked

        if (checked) {
            stopPresenter.getStops(companyNumber, routeId, direction2, daysActive)
        } else {
            stopPresenter.getStops(companyNumber, routeId, direction1, daysActive)
        }
    }
}
