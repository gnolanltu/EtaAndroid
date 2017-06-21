package com.riis.simple.etaandroid.presenter.interfaces

interface JsonFetcherInterface {
    @Throws(Exception::class)
    fun fetchUrl(urlString: String): String
}
