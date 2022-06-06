package com.application.kgtuapp.Network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request

object Network {
    val okHttpClient: OkHttpClient = OkHttpClient()

    fun getData():Call{
        val URL = "https://api.kstuapp.ru/v1/structure/"
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

    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        var isOnline: Boolean = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                isOnline = true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                isOnline = true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                isOnline = true
            } else {
                Log.i("Internet", "There are no internet connection")
            }
        } else {
            Log.i("Internet", "capabilities == null")
        }
        return isOnline
    }
}