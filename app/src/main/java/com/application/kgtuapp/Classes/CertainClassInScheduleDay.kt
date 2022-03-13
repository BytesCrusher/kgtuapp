package com.application.kgtuapp.Classes

//Здесь описывается конкретная пара, которая в дальнейшем кладется в день расписания
//И день расписания затем собирается из набора таких пар
//
class CertainClassInScheduleDay(
    val id: Int,
    val idScheduleDay: Int,
    val idClassNumber: Int?,
    val idClassAudience: Int,
    val idClassType: Int,
    val idClassName: Int
) {



    fun getClassStartEndTimeByIdClassNumber():String{
        return "23"
    }

    fun getClassNumberId():Int?{
        return idClassNumber
    }

    override fun toString(): String {
        return "$id, $idScheduleDay, $idClassNumber, $idClassAudience, $idClassType, $idClassName"

    }

}