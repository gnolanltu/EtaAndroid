package com.riis.simple.etaandroid.presenter

import com.riis.simple.etaandroid.model.StopModel
import com.riis.simple.etaandroid.presenter.interfaces.StopPresenter
import com.riis.simple.etaandroid.view.interfaces.StopView

class StopPresenterImpl(val view: StopView) : StopPresenter {
    override fun getStops(companyNumber: Int, routeId: String, direction: String, daysActive: String) {
        val model = StopModel(this)
        model.getStops(companyNumber, routeId, direction, daysActive)
    }

    override fun onWebCallStart() {
        view.showProgressDialog()
    }

    override fun onWebCallComplete(stopsList: List<String>) {
        view.loadStops(stopsList)
    }
}