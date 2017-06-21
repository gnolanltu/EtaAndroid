package com.riis.simple.etaandroid.presenter.interfaces

import com.riis.simple.etaandroid.model.Route
import org.json.JSONException
import java.util.ArrayList

interface JsonParserInterface {
    @Throws(JSONException::class)
    fun parseRoutes(jsonString: String): ArrayList<Route>

    @Throws(JSONException::class)
    fun getStops(jsonString: String): ArrayList<String>
}