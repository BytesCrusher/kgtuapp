package com.application.kgtuapp.screens.schedule

import retrofit2.http.Body
import retrofit2.http.POST

interface ScheduleGoApi {
    @POST("/schedule/")
    suspend fun sendElementRequest(@Body elementModel: RetrofitCreatePostScheduleRequest)
}