package com.riis.simple.etaandroid.controllers.stop

import android.os.AsyncTask
import com.riis.simple.etaandroid.callback.GetStopsCallback
import com.riis.simple.etaandroid.model.StopModel
import org.json.JSONException
import java.io.FileNotFoundException
import java.util.*

class GetStopsAsyncTask(val companyNumber: Int, val callback: GetStopsCallback) : AsyncTask<String, Void, List<String>>() {
    override fun onPreExecute() {
        super.onPreExecute()
        callback.onStart()
    }

    override fun doInBackground(vararg params: String?): List<String> {
        if (params.count() != 3) {
            return ArrayList()
        }

        try {
            val stopModel = StopModel()
            return stopModel.getStops(companyNumber, params[0]!!, params[1]!!, params[2]!!)
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