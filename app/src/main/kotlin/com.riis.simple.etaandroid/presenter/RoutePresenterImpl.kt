package com.riis.simple.etaandroid.presenter

import android.os.AsyncTask
import com.riis.simple.etaandroid.model.Route
import com.riis.simple.etaandroid.model.UrlStringBuilder
import com.riis.simple.etaandroid.presenter.interfaces.JsonFetcherInterface
import com.riis.simple.etaandroid.presenter.interfaces.JsonParserInterface
import com.riis.simple.etaandroid.presenter.interfaces.RoutePresenter
import com.riis.simple.etaandroid.view.interfaces.RouteView
import org.json.JSONException
import java.io.FileNotFoundException
import java.util.*

class RoutePresenterImpl(val view: RouteView) : RoutePresenter {
    override fun getRoutes(companyNumber: Int) {
        GetRoutesAsyncTask(this).execute(companyNumber)
    }

    override fun onWebCallStart() {
        view.showProgressDialog()
    }

    override fun onWebCallComplete(routeList: List<Route>) {
        view.loadRoutes(routeList)
    }

    override fun onRouteRowClicked(route: Route) {
        view.navigateToStops(route.companyId!!, route.id!!, route.direction1, route.daysActive)
    }

    private inner class GetRoutesAsyncTask(val presenter: RoutePresenter) : AsyncTask<Int, Void, List<Route>>() {

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

        override fun doInBackground(vararg params: Int?): List<Route>? {
            if (params.count() != 1) {
                return ArrayList()
            }

            try {
                val routesJson = fetcher!!.fetchUrl(UrlStringBuilder.getRoutesUrl(params[0]!!))
                return parser!!.parseRoutes(routesJson)
            } catch (e: JSONException) {
                return ArrayList()
            } catch (e: FileNotFoundException) {
                return ArrayList()
            }
        }

        override fun onPostExecute(result: List<Route>) {
            super.onPostExecute(result)
            presenter.onWebCallComplete(result)
        }
    }
}