package com.riis.simple.etaandroid.controllers.route

import android.os.AsyncTask
import com.riis.simple.etaandroid.callback.GetRoutesCallback
import com.riis.simple.etaandroid.model.Route
import com.riis.simple.etaandroid.model.RouteModel
import org.json.JSONException
import java.util.*

class GetRoutesAsyncTask(val callback: GetRoutesCallback) : AsyncTask<Int, Void, List<Route>>() {
    override fun onPreExecute() {
        super.onPreExecute()
        callback.onStart()
    }

    override fun doInBackground(vararg params: Int?): List<Route>? {
        if (params.count() != 1) {
            return ArrayList()
        }

        try {
            val routeModel = RouteModel()
            return routeModel.getCompanyRoutes(params[0] as Int)
        } catch (e: JSONException) {
            return ArrayList()
        }
    }

    override fun onPostExecute(result: List<Route>) {
        super.onPostExecute(result)
        callback.onComplete(result)
    }
}
