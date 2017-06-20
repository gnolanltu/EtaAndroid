package com.riis.simple.etaandroid.view

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ListView
import com.riis.simple.etaandroid.R
import com.riis.simple.etaandroid.adapters.RoutesAdapter
import com.riis.simple.etaandroid.controllers.RoutesController
import com.riis.simple.etaandroid.model.Route

class RouteActivity : AppCompatActivity() {

    companion object {
        val EXTRA_COMPANY = "company"
    }

    private var routeList: ListView? = null
    private var progressDialog: ProgressDialog? = null

    private var controller: RoutesController = RoutesController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)
        val companyNumber = intent.getIntExtra(EXTRA_COMPANY, -1)

        routeList = findViewById(R.id.routes) as ListView

        controller.getRoutes(companyNumber)

        routeList!!.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val route = routeList!!.adapter.getItem(position) as Route

            val stopIntent = Intent(this@RouteActivity, StopActivity::class.java)
            stopIntent.putExtra(StopActivity.EXTRA_COMPANY, route.companyId)
            stopIntent.putExtra(StopActivity.EXTRA_ROUTEID, route.id)
            stopIntent.putExtra(StopActivity.EXTRA_DIRECTION, route.direction1)
            stopIntent.putExtra(StopActivity.EXTRA_DAYSACTIVE, route.daysActive)
            startActivity(stopIntent)
        }
    }

    fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
            progressDialog!!.setTitle("Loading Routes")
            progressDialog!!.setCancelable(false)
        }

        progressDialog!!.show()
    }

    fun loadRoutes(routeResultList: List<Route>) {
        if (progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }

        routeList!!.adapter = RoutesAdapter(this, routeResultList)
    }
}
