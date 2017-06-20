package com.riis.simple.etaandroid.model

import com.riis.simple.etaandroid.model.api.JsonFetcher
import com.riis.simple.etaandroid.model.api.JsonParser
import com.riis.simple.etaandroid.model.api.UrlStringBuilder

class StopModel {
    fun getStops(companyNumber: Int, routeId: String,
                 direction: String, daysActive: String) : List<String> {
        val routesJson = JsonFetcher.fetchUrl(
                UrlStringBuilder.getStopsUrl(companyNumber, routeId, direction, daysActive))
        return JsonParser.getStops(routesJson)
    }
}