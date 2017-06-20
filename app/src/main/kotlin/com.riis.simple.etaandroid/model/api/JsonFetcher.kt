package com.riis.simple.etaandroid.model.api

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

object JsonFetcher {

    //Download the URL as text
    @Throws(Exception::class)
    fun fetchUrl(urlString: String): String {
        var reader: BufferedReader? = null
        try {
            val url = URL(urlString)
            reader = BufferedReader(InputStreamReader(url.openStream()))
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
