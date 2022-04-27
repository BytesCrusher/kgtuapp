package com.application.kgtuapp.Classes

import kotlin.random.Random

class ScheduleTwoWeek() {
    //в этом классе идет заполнение и дальнейшее хранение мапы с расписанием
    //мапа с расписанием имеет типы <Int, MutableList<CertainClassInScheduleDay>>()
    //где MutableList<CertainClassInScheduleDay> - это список конкретных пар,
    //где CertainClassInScheduleDay - конкретная пара, которая хранит в себе всю инфу
    //данные будут выгружаться из таблиц БД, на данный момент рандомная генерация из заглушек

    val classNameList = listOf<String>(
        "Технологические машины и оборудование",
        "Операционные системы",
        "Математическая логика и теория алгоритмов",
        "Практическая подготовка по физической культуре и занятия спортом (элективные курсы)",
        "Иностранный язык",
        "Методы научных исследований"
    )

    val classTypeMap = mapOf<Int, String>(
        0 to "лекции",
        1 to "практические",
        2 to "лабораторные",
        3 to "экзамен"
    )

    val audienceList = listOf<String>("261.6", "261.17", "382", "266", "256", "230")

    val scheduleMap = mutableMapOf<Int, MutableList<CertainClassInScheduleDay>>()

    //заполнить информацией 2-х недельное расписание
    fun createScheduleTwoWeek(): MutableMap<Int, MutableList<CertainClassInScheduleDay>> {
        for (i in 0..13) {
            val scheduleDay = addDataToScheduleDay(i)
            scheduleMap[i] = scheduleDay
        }
        return scheduleMap
    }

    //заполнить информацией конкретный день
    private fun addDataToScheduleDay(idScheduleDay: Int): MutableList<CertainClassInScheduleDay> {
        val listOfClasses = mutableListOf<CertainClassInScheduleDay>()

        val classCount = Random.nextInt(0, 7)//количество пар, которое будет сгенерено

        if (classCount != 0) {
            for (i in (0..classCount)) {
                val classNumberId =
                    i//classNumberMap[Random.nextInt(0,classNumberMap.size-1)]?.getId()
                val classAudienceId = Random.nextInt(
                    0,
                    audienceList.size
                )//audienceList.indexOf(audienceList[Random.nextInt(0,audienceList.size-1)])
                val classTypeId = Random.nextInt(0, classTypeMap.size)

                val className = classNameList[Random.nextInt(
                    0,
                    classNameList.size
                )]//[Random.nextInt(0, classNameList.size-1)]

                val newClass = CertainClassInScheduleDay(
                    i,
                    idScheduleDay,
                    classNumberId,
                    classAudienceId,
                    classTypeId,
                    className
                )
                listOfClasses.add(newClass)
            }
        }
        return listOfClasses
    }
}