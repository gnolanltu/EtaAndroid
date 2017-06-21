package com.riis.simple.etaandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.riis.simple.etaandroid.repository.StopRepositoryImp
import com.riis.simple.etaandroid.repository.interfaces.StopRepository
import com.riis.simple.etaandroid.view.interfaces.StopView
import com.riis.simple.etaandroid.viewmodel.interfaces.StopViewModelInterface

class StopViewModel : ViewModel(), StopViewModelInterface {

    var view: StopView? = null

    private var stopsList: MutableLiveData<List<String>> = MutableLiveData()
    private var repository: StopRepository? = null

    init {
        repository = StopRepositoryImp(this)
    }

    fun getStopList() : MutableLiveData<List<String>> {
        return stopsList
    }

    fun getStops(companyNumber: Int, routeId: String, direction: String, daysActive: String) {
        repository!!.getStops(companyNumber, routeId, direction, daysActive)
    }

    override fun onWebCallStart() {
        view!!.showProgressDialog()
    }

    override fun onWebCallComplete(stopsList: List<String>) {
        this.stopsList.value = stopsList
    }
}