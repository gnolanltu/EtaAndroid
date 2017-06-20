package com.riis.simple.etaandroid.controllers

import com.riis.simple.etaandroid.R
import com.riis.simple.etaandroid.view.CompanyActivity

class CompanyController(val activity: CompanyActivity) {
    fun getCompanies(): Array<String> {
        return activity.resources.getStringArray(R.array.busnames)!!
    }
}
