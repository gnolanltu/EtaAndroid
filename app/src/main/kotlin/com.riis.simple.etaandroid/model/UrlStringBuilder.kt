package com.riis.simple.etaandroid.model

object UrlStringBuilder {
    fun getRoutesUrl(company: Int): String {
        return "http://ec2-204-236-211-33.compute-1.amazonaws.com:8080/companies/$company/routes"
    }

    fun getStopsUrl(companyNumber: Int, routeId: String, direction: String, daysActive: String): String {
        return "http://ec2-204-236-211-33.compute-1.amazonaws.com:8080/companies/" +
                companyNumber + "/routes/" + routeId + "/" + direction + "/" + daysActive + "/1/stops"
    }
}
