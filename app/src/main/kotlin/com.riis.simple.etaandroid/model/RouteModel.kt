package com.riis.simple.etaandroid.model

import android.os.AsyncTask
import com.riis.simple.etaandroid.callback.GetRoutesCallback
import com.riis.simple.etaandroid.model.api.JsonFetcher
import com.riis.simple.etaandroid.model.api.JsonParser
import com.riis.simple.etaandroid.model.api.UrlStringBuilder
import org.json.JSONException
import java.io.FileNotFoundException
import java.util.*

class RouteModel(val callback: GetRoutesCallback) {
    fun getCompanyRoutes(companyNumber: Int) {
        GetRoutesAsyncTask(callback).execute(companyNumber)
    }

    private inner class GetRoutesAsyncTask(val callback: GetRoutesCallback) : AsyncTask<Int, Void, List<Route>>() {
        override fun onPreExecute() {
            super.onPreExecute()
            callback.onStart()
        }

        override fun doInBackground(vararg params: Int?): List<Route>? {
            if (params.count() != 1) {
                return ArrayList()
            }

            try {
                val routesJson = JsonFetcher.fetchUrl(UrlStringBuilder.getRoutesUrl(params[0]!!))
                return JsonParser.parseRoutes(routesJson)
            } catch (e: JSONException) {
                return ArrayList()
            } catch (e: FileNotFoundException) {
                return ArrayList()
            }
        }

        override fun onPostExecute(result: List<Route>) {
            super.onPostExecute(result)
            callback.onComplete(result)
        }
    }
}
