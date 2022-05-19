package com.application.kgtuapp.screens.schedule

import com.application.kgtuapp.screens.schedule.data.Example
import retrofit2.http.Body
import retrofit2.http.POST

interface ScheduleGoApi {
    @POST("/schedule/")
    suspend fun sendElementRequest(@Body elementModel: RetrofitCreatePostScheduleRequest) : Example
}