package com.riis.simple.etaandroid.view

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ToggleButton
import com.riis.simple.etaandroid.controllers.stop.StopsController

class StopActivity : AppCompatActivity() {

    companion object {
        val EXTRA_COMPANY = "company"
        val EXTRA_ROUTEID = "routeId"
        val EXTRA_DIRECTION = "direction1"
        val EXTRA_DAYSACTIVE = "daysActive"
    }

    private var routeId: String? = null
    private var currentDirection: String? = null
    private var direction1: String? = null
    private var direction2: String? = null
    private var daysActive: String? = null

    private var stopsListView: ListView? = null
    private var progressDialog: ProgressDialog = ProgressDialog(this)
    private var companyNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.riis.simple.etaandroid.R.layout.activity_stop)

        companyNumber = intent.getIntExtra(EXTRA_COMPANY, -1)
        routeId = intent.getStringExtra(EXTRA_ROUTEID)
        direction1 = intent.getStringExtra(EXTRA_DIRECTION)
        daysActive = intent.getStringExtra(EXTRA_DAYSACTIVE)

        val controller = StopsController(this)

        stopsListView = findViewById(com.riis.simple.etaandroid.R.id.stops) as ListView

        //if daysActive has more than one value, grab first value
        val commaIndex = daysActive!!.indexOf(",")
        if (commaIndex != -1) {
            daysActive = daysActive!!.substring(0, commaIndex)
        }

        //Always show North or Eastbound first for consistency
        when (direction1!!.toLowerCase()) {
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

        val directionButton = findViewById(com.riis.simple.etaandroid.R.id.directionButton) as ToggleButton
        directionButton.textOff = direction1
        directionButton.textOn = direction2
        directionButton.isChecked = false
        directionButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                currentDirection = direction2
            } else {
                currentDirection = direction1
            }

            controller.getStops(companyNumber, routeId!!, currentDirection!!, daysActive!!)
        }

        currentDirection = direction1

        controller.getStops(companyNumber, routeId!!, currentDirection!!, daysActive!!)
    }

    fun showProgressDialog() {
        progressDialog.setTitle("Loading Routes")
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    fun loadStops(stopsResultList: List<String>) {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }

        stopsListView!!.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stopsResultList)
    }
}
