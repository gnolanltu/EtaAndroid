package com.riis.simple.etaandroid.repository

import android.os.AsyncTask
import com.riis.simple.etaandroid.model.Route
import com.riis.simple.etaandroid.model.UrlStringBuilder
import com.riis.simple.etaandroid.repository.interfaces.JsonFetcherInterface
import com.riis.simple.etaandroid.repository.interfaces.JsonParserInterface
import com.riis.simple.etaandroid.viewmodel.interfaces.RouteViewModelInterface
import com.riis.simple.etaandroid.repository.interfaces.RouteRepository
import org.json.JSONException
import java.io.FileNotFoundException
import java.util.ArrayList

class RouteRepositoryImp(val viewModelInterface: RouteViewModelInterface) : RouteRepository {
    override fun getRoutes(companyId: Int) {
        GetRoutesAsyncTask(viewModelInterface).execute(companyId)
    }

    private inner class GetRoutesAsyncTask(val viewModelInterface: RouteViewModelInterface) : AsyncTask<Int, Void, List<Route>>() {

        private var fetcher: JsonFetcherInterface? = null
        private var parser: JsonParserInterface? = null

        init {
            fetcher = JsonFetcher
            parser = JsonParser
        }

        override fun onPreExecute() {
            super.onPreExecute()
            viewModelInterface.onWebCallStart()
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
            viewModelInterface.onWebCallComplete(result)
        }
    }
}