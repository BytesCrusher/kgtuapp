package com.application.kgtuapp.Network

import com.application.kgtuapp.DataClasses.RemoteSchedule
import retrofit2.http.GET

interface DotaApi {

    @GET("./heroes")
    suspend fun fetchHeroes(): List<RemoteSchedule>
}