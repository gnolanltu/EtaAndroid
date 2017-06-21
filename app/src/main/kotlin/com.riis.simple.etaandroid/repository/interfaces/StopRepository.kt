package com.riis.simple.etaandroid.repository.interfaces

interface StopRepository {
    fun getStops(companyNumber: Int, routeId: String, direction: String, daysActive: String)
}