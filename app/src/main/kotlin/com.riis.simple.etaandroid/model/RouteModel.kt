package com.riis.simple.etaandroid.model

import com.riis.simple.etaandroid.model.api.JsonFetcher
import com.riis.simple.etaandroid.model.api.JsonParser
import com.riis.simple.etaandroid.model.api.UrlStringBuilder

class RouteModel {
    fun getCompanyRoutes(companyNumber: Int) : List<Route> {
        val routesJson = JsonFetcher.fetchUrl(UrlStringBuilder.getRoutesUrl(companyNumber))
        return JsonParser.parseRoutes(routesJson)
    }
}
