package com.riis.simple.etaandroid.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.riis.simple.etaandroid.model.Route
import com.riis.simple.etaandroid.viewmodel.interfaces.RouteViewModelInterface
import com.riis.simple.etaandroid.repository.RouteRepositoryImp
import com.riis.simple.etaandroid.repository.interfaces.RouteRepository
import com.riis.simple.etaandroid.view.interfaces.RouteView

class RouteViewModel : ViewModel(), RouteViewModelInterface {
    var routeView: RouteView? = null

    private var routeList: MutableLiveData<List<Route>> = MutableLiveData()
    private var routeRepository: RouteRepository? = null

    init {
        routeRepository = RouteRepositoryImp(this)
    }

    fun getRouteList(): LiveData<List<Route>> {
        return routeList
    }

    fun getRoutes(companyNumber: Int) {
        routeRepository!!.getRoutes(companyNumber)
    }

    override fun onWebCallStart() {
        routeView!!.showProgressDialog()
    }

    override fun onWebCallComplete(routeList: List<Route>) {
        this.routeList.value = routeList
    }
}