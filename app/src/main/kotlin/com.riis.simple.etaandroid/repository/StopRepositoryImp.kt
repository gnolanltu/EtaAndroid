package com.riis.simple.etaandroid.repository

import android.os.AsyncTask
import com.riis.simple.etaandroid.model.UrlStringBuilder
import com.riis.simple.etaandroid.repository.interfaces.JsonFetcherInterface
import com.riis.simple.etaandroid.repository.interfaces.JsonParserInterface
import com.riis.simple.etaandroid.repository.interfaces.StopRepository
import com.riis.simple.etaandroid.viewmodel.interfaces.StopViewModelInterface
import org.json.JSONException
import java.io.FileNotFoundException
import java.util.*

class StopRepositoryImp(val viewModel: StopViewModelInterface) : StopRepository {

    override fun getStops(companyNumber: Int, routeId: String, direction: String, daysActive: String) {
        GetStopsAsyncTask(companyNumber, viewModel).execute(routeId, direction, daysActive)
    }

    private inner class GetStopsAsyncTask(val companyNumber: Int, val viewModel: StopViewModelInterface)
        : AsyncTask<String, Void, List<String>>() {

        private var fetcher: JsonFetcherInterface? = null
        private var parser: JsonParserInterface? = null

        init {
            fetcher = JsonFetcher
            parser = JsonParser
        }

        override fun onPreExecute() {
            super.onPreExecute()
            viewModel.onWebCallStart()
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
            viewModel.onWebCallComplete(result!!)
        }
    }
}