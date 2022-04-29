package com.application.kgtuapp.Network

import com.application.kgtuapp.Interfaces.Api
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.converter.moshi.moshiConverterFactory
import retrofit2.create

object scheduleNetworking {

    //создаем клиент
    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor (
                    HttpLoggingInterceptor()
                        .setLevel((HttpLoggingInterceptor.Level.BODY))
                )
        .build()
    //https://api.kstuapp.ru//timetable/
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.kstuapp.ru/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    val postListScheduleApi: Api
        get() = retrofit.create()
}