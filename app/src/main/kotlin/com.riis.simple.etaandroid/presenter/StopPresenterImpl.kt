package com.riis.simple.etaandroid.presenter

import android.os.AsyncTask
import com.riis.simple.etaandroid.model.UrlStringBuilder
import com.riis.simple.etaandroid.presenter.interfaces.JsonFetcherInterface
import com.riis.simple.etaandroid.presenter.interfaces.JsonParserInterface
import com.riis.simple.etaandroid.presenter.interfaces.StopPresenter
import com.riis.simple.etaandroid.view.interfaces.StopView
import org.json.JSONException
import java.io.FileNotFoundException
import java.util.*

class StopPresenterImpl(val view: StopView) : StopPresenter {
    override fun getStops(companyNumber: Int, routeId: String, direction: String, daysActive: String) {
        GetStopsAsyncTask(companyNumber, this).execute(routeId, direction, daysActive)
    }

    override fun onWebCallStart() {
        view.showProgressDialog()
    }

    override fun onWebCallComplete(stopsList: List<String>) {
        view.loadStops(stopsList)
    }

    private inner class GetStopsAsyncTask(val companyNumber: Int, val presenter: StopPresenter)
        : AsyncTask<String, Void, List<String>>() {

        private var fetcher: JsonFetcherInterface? = null
        private var parser: JsonParserInterface? = null

        init {
            fetcher = JsonFetcher
            parser = JsonParser
        }

        override fun onPreExecute() {
            super.onPreExecute()
            presenter.onWebCallStart()
        }

        override fun doInBackground(vararg params: String?): List<String> {
            if (params.count() != 3) {
                return ArrayList()
            }

            try {
                val routesJson = fetcher!!.fetchUrl(
                        UrlStringBuilder.getStopsUrl(companyNumber, params[0]!!, params[1]!!, params[2]!!))
                return parser!!.getStops(routesJson)
            } catch (e: JSONException) {
                return ArrayList()
            } catch (e: FileNotFoundException) {
                return ArrayList()
            }
        }

        override fun onPostExecute(result: List<String>?) {
            super.onPostExecute(result)
            presenter.onWebCallComplete(result!!)
        }
    }
}