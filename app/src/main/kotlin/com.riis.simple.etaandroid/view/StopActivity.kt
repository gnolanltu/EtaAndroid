package com.riis.simple.etaandroid.view

import android.app.ProgressDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ToggleButton
import com.riis.simple.etaandroid.R
import com.riis.simple.etaandroid.adapters.StopAdapter
import com.riis.simple.etaandroid.databinding.ActivityStopBinding
import com.riis.simple.etaandroid.view.interfaces.StopView
import com.riis.simple.etaandroid.viewmodel.StopActivityViewModel


class StopActivity : AppCompatActivity(), StopView {
    companion object {
        val EXTRA_COMPANY = "company"
        val EXTRA_ROUTEID = "routeId"
        val EXTRA_DIRECTION = "direction1"
        val EXTRA_DAYSACTIVE = "daysActive"
    }

    private var direction1: String? = null
    private var direction2: String? = null

    private var progressDialog: ProgressDialog? = null

    private var binding: ActivityStopBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = StopActivityViewModel(intent, this)
        binding = DataBindingUtil.setContentView<ActivityStopBinding>(this, R.layout.activity_stop)

        binding!!.viewModel = viewModel

        viewModel.onCheckChanged()

        direction1 = intent.getStringExtra(EXTRA_DIRECTION)

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
    }

    override fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
            progressDialog!!.setTitle("Loading Stops")
            progressDialog!!.setCancelable(false)
        }

        progressDialog!!.show()
    }

    override fun loadStops(stopsResultList: List<String>) {
        if (progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }

        binding!!.stops.adapter = StopAdapter(stopsResultList)
    }
}
