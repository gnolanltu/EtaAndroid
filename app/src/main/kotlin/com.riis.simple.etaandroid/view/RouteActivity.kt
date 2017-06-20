package com.riis.simple.etaandroid.view

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ListView
import com.riis.simple.etaandroid.R
import com.riis.simple.etaandroid.adapters.RoutesAdapter
import com.riis.simple.etaandroid.model.Route
import com.riis.simple.etaandroid.presenter.RoutePresenterImpl
import com.riis.simple.etaandroid.presenter.interfaces.RoutePresenter
import com.riis.simple.etaandroid.view.interfaces.RouteView

class RouteActivity : AppCompatActivity(), RouteView {
    companion object {
        val EXTRA_COMPANY = "company"
    }

    private var routeList: ListView? = null
    private var progressDialog: ProgressDialog? = null

    private var presenter: RoutePresenter = RoutePresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)
        val companyNumber = intent.getIntExtra(EXTRA_COMPANY, -1)

        routeList = findViewById(R.id.routes) as ListView?

        presenter.getRoutes(companyNumber)

        routeList!!.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val route = routeList!!.adapter.getItem(position) as Route

            presenter.onRouteRowClicked(route)
        }
    }

    override fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
            progressDialog!!.setTitle("Loading Routes")
            progressDialog!!.setCancelable(false)
        }

        progressDialog!!.show()
    }

    override fun loadRoutes(routeResultList: List<Route>) {
        if (progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }

        routeList!!.adapter = RoutesAdapter(this, routeResultList)
    }

    override fun navigateToStops(companyId: Int, routeId: Long, direction: String, daysActive: String) {
        val stopIntent = Intent(this@RouteActivity, StopActivity::class.java)
        stopIntent.putExtra(StopActivity.EXTRA_COMPANY, companyId)
        stopIntent.putExtra(StopActivity.EXTRA_ROUTEID, routeId)
        stopIntent.putExtra(StopActivity.EXTRA_DIRECTION, direction)
        stopIntent.putExtra(StopActivity.EXTRA_DAYSACTIVE, daysActive)
        startActivity(stopIntent)
    }
}
