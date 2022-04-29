package com.application.kgtuapp.Repositories

import com.application.kgtuapp.Classes.ServerItemsWrapper
import com.application.kgtuapp.DataClasses.RemoteSchedule
import com.application.kgtuapp.Network.scheduleNetworking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleRepository {

    fun searchSchedule(
        query: String,
        onComplete: (List<RemoteSchedule>) -> Unit,
        onError: (Throwable) -> Unit
    ){
        scheduleNetworking.postListScheduleApi.searchSchedule(query).enqueue(
            object : Callback<ServerItemsWrapper<RemoteSchedule>> {
                override fun onResponse(
                    call: Call<ServerItemsWrapper<RemoteSchedule>>,
                    response: Response<ServerItemsWrapper<RemoteSchedule>>
                ) {
                    if (response.isSuccessful){
                        onComplete(response.body()?.items.orEmpty())
                    } else {
                        onError(RuntimeException("incorrect status code"))
                    }
                }

                override fun onFailure(
                    call: Call<ServerItemsWrapper<RemoteSchedule>>,
                    t: Throwable
                ) {
                    onError(t)
                }

            }
        )

    }
}