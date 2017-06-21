package com.riis.simple.etaandroid.viewmodel

import android.content.Context
import android.content.Intent
import com.riis.simple.etaandroid.model.Company
import com.riis.simple.etaandroid.view.RouteActivity
import java.util.*

class CompanyViewModel(val context: Context, val company: Company) : Observable() {
    var companyName: String = company.name

    fun onItemSelected() {
        val intent = Intent(context, RouteActivity::class.java)
        intent.putExtra(RouteActivity.EXTRA_COMPANY, company.id)
        context.startActivity(intent)
    }
}