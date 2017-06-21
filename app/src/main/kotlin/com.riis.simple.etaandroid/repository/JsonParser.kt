package com.riis.simple.etaandroid.repository

import com.riis.simple.etaandroid.model.Route
import com.riis.simple.etaandroid.repository.interfaces.JsonParserInterface
import org.json.JSONArray
import org.json.JSONException
import java.util.*

object JsonParser : JsonParserInterface {

    @Throws(JSONException::class)
    override fun parseRoutes(jsonString: String): ArrayList<Route> {
        //Create jsonArray for routeIDs
        val routeID = JSONArray(jsonString)

        //Get number of routes
        val n = routeID.length()

        //for each route enter it into an array
        val returnArray = ArrayList<Route>()
        for (i in 0..n - 1) {
            val instance = routeID.getJSONObject(i)

            val route = Route()
            route.id = instance.getLong("id")
            route.routeId = instance.getString("routeID")
            route.daysActive = instance.getString("daysActive")
            route.direction1 = instance.getString("direction1")
            route.routeName = instance.getString("routeName")
            route.companyId = instance.getInt("companyID")

            returnArray.add(route)
        }

        return returnArray
    }

    @Throws(JSONException::class)
    override fun getStops(jsonString: String): ArrayList<String> {
        //Create jsonArray for routeIDs
        val stops = JSONArray(jsonString)

        //Get number of stops
        val n = stops.length()

        //for each route enter it into an array
        val returnArray = (0..n - 1)
                .map { stops.getJSONObject(it) }
                .mapTo(ArrayList<String>()) { it.getString("stopName") }
        return returnArray
    }
}
