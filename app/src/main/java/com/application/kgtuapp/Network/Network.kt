package com.application.kgtuapp.Network

import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request

object Network {
    val okHttpClient: OkHttpClient = OkHttpClient()

    fun getData():Call{
        val URL = "https://api.kstuapp.ru/structure/"
        /*val URL = HttpUrl.Builder()
            .scheme("https")
            .host("api.icndb.com")
            //.addEncodedQueryParameter("apikey", "fff")
            .build()*/

        val request: Request = Request.Builder()
            .url(URL)
            .build()

        return okHttpClient.newCall(request)
    }
}