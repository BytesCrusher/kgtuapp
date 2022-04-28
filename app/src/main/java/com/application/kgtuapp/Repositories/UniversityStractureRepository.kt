package com.application.kgtuapp.Repositories

import com.application.kgtuapp.Classes.CertainClassInScheduleDay
import okhttp3.HttpUrl
import okhttp3.Request

class UniversityStractureRepository {
    /*fun getUniversityStracture(
        callback: List<>
    )*/

    //Конфигурируем URL
    val url = HttpUrl.Builder()
        .build()

    fun searchGroupSchedule(groupName: String, callback: (List<CertainClassInScheduleDay>) -> Unit){
        //для совершения запроса есть класс
        /*val request = Request.Builder()
            .post()
            .url()
            .build()*/
    }
}