package com.riis.simple.etaandroid.presenter

import com.riis.simple.etaandroid.presenter.interfaces.CompanyPresenter
import com.riis.simple.etaandroid.view.interfaces.CompanyView

class CompanyPresenterImpl(val view: CompanyView) : CompanyPresenter {
    override fun onCompanyRowClicked(position: Int) {
        view.navigateToRoutes(position + 1)
    }
}