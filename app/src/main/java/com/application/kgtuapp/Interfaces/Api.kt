package com.application.kgtuapp.Interfaces

import com.application.kgtuapp.Classes.ServerItemsWrapper
import com.application.kgtuapp.DataClasses.RemoteSchedule
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @POST("timetable")
    fun searchSchedule(
        //@Query("q") query: String
    //@Path()
    @Body body: String
    ): Call<ServerItemsWrapper<RemoteSchedule>>

    class ReactionCreateBody(val content: Int)

}