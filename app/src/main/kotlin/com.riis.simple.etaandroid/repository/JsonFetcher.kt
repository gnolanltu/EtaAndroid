package com.riis.simple.etaandroid.repository

import com.riis.simple.etaandroid.repository.interfaces.JsonFetcherInterface
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

object JsonFetcher : com.riis.simple.etaandroid.repository.interfaces.JsonFetcherInterface {

    //Download the URL as text
    @Throws(Exception::class)
    override fun fetchUrl(urlString: String): String {
        var reader: java.io.BufferedReader? = null
        try {
            val url = java.net.URL(urlString)
            reader = java.io.BufferedReader(java.io.InputStreamReader(url.openStream()))
            val builder = StringBuilder()
            val chars = CharArray(1024)

            var read = reader.read(chars)
            while (read != -1) {
                builder.append(chars, 0, read)
                read = reader.read(chars)
            }

            return builder.toString()
        } finally {
            if (reader != null)
                reader.close()
        }
    }
}
