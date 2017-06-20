package com.riis.simple.etaandroid.model

import android.os.AsyncTask
import com.riis.simple.etaandroid.callback.GetStopsCallback
import com.riis.simple.etaandroid.model.api.JsonFetcher
import com.riis.simple.etaandroid.model.api.JsonParser
import com.riis.simple.etaandroid.model.api.UrlStringBuilder
import org.json.JSONException
import java.io.FileNotFoundException
import java.util.*

class StopModel(val callback: GetStopsCallback) {
    fun getStops(companyNumber: Int, routeId: String, direction: String, daysActive: String) {
        GetStopsAsyncTask(companyNumber, callback).execute(routeId, direction, daysActive)
    }

    private inner class GetStopsAsyncTask(val companyNumber: Int, val callback: GetStopsCallback)
        : AsyncTask<String, Void, List<String>>() {
        override fun onPreExecute() {
            super.onPreExecute()
            callback.onStart()
        }

        override fun doInBackground(vararg params: String?): List<String> {
            if (params.count() != 3) {
                return ArrayList()
            }

            try {
                val routesJson = JsonFetcher.fetchUrl(
                        UrlStringBuilder.getStopsUrl(companyNumber, params[0]!!, params[1]!!, params[2]!!))
                return JsonParser.getStops(routesJson)
            } catch (e: JSONException) {
                return ArrayList()
            } catch (e: FileNotFoundException) {
                return ArrayList()
            }
        }

        override fun onPostExecute(result: List<String>?) {
            super.onPostExecute(result)
            callback.onComplete(result!!)
        }
    }
}