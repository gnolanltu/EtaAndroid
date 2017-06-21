package com.riis.simple.etaandroid.repository.interfaces

interface JsonFetcherInterface {
    @Throws(Exception::class)
    fun fetchUrl(urlString: String): String
}
