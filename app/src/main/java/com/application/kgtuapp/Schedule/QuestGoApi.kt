package com.application.kgtuapp.Schedule

import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface QuestGoApi {

    @POST("/schedule/")
    suspend fun sendElementRequest(@Body elementModel: RetrofitCreateElementRequest)

    /*@POST("/createElement")
    suspend fun sendElementRequest(@Body elementModel: RetrofitCreateElementRequest)*/

    /*@POST("/sendPhoto")
    @Multipart
    suspend fun sendFile(@Part body: MultipartBody.Part)*/
}