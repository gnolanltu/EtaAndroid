package com.riis.simple.etaandroid.view

import android.app.ProgressDialog
import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import com.riis.simple.etaandroid.R
import com.riis.simple.etaandroid.model.Route
import com.riis.simple.etaandroid.view.adapters.RoutesAdapter
import com.riis.simple.etaandroid.view.interfaces.RouteView
import com.riis.simple.etaandroid.viewmodel.RouteViewModel

class RouteActivity : LifecycleActivity(), RouteView {
    companion object {
        val EXTRA_COMPANY = "company"
    }

    private var routeList: ListView? = null
    private var progressDialog: ProgressDialog? = null

    private var viewModel: RouteViewModel?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)
        val companyNumber = intent.getIntExtra(EXTRA_COMPANY, -1)

        routeList = findViewById(R.id.routes) as ListView?

        viewModel = ViewModelProviders.of(this).get(RouteViewModel::class.java)
        viewModel!!.routeView = this
        subscribe()

        viewModel!!.getRoutes(companyNumber)

        routeList!!.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val route = routeList!!.adapter.getItem(position) as Route

            navigateToStops(route.companyId!!, route.id!!, route.direction1, route.daysActive)
        }
    }

    private fun subscribe() {
        val routeListObserver = Observer<List<Route>> { routeResultList ->
            if (progressDialog!!.isShowing) {
                progressDialog!!.dismiss()
            }

            routeList!!.adapter = RoutesAdapter(this@RouteActivity, routeResultList!!)
        }

        viewModel!!.getRouteList().observe(this, routeListObserver)
    }

    override fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
            progressDialog!!.setTitle("Loading Routes")
            progressDialog!!.setCancelable(false)
        }

        progressDialog!!.show()
    }

    private fun navigateToStops(companyId: Int, routeId: Long, direction: String, daysActive: String) {
        val stopIntent = Intent(this@RouteActivity, StopActivity::class.java)
        stopIntent.putExtra(StopActivity.EXTRA_COMPANY, companyId)
        stopIntent.putExtra(StopActivity.EXTRA_ROUTEID, routeId)
        stopIntent.putExtra(StopActivity.EXTRA_DIRECTION, direction)
        stopIntent.putExtra(StopActivity.EXTRA_DAYSACTIVE, daysActive)
        startActivity(stopIntent)
    }
}
