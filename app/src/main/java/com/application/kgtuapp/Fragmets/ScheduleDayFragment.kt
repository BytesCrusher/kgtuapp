package com.application.kgtuapp.Fragmets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.kgtuapp.Classes.CertainClassInScheduleDay
import com.application.kgtuapp.Classes.CertainClassStartEndTime
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.FragmentScheduleDayBinding
import kotlin.random.Random

class ScheduleDayFragment : Fragment(R.layout.fragment_schedule_day) {
    private lateinit var binding: FragmentScheduleDayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleDayBinding.inflate(layoutInflater, container, false)

        //ниже костыли
        binding.textView2.text= b
        //конец костылей

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ScheduleDayFragment()
    }

    //Здесь пока в костыльном варианте идет генерация дня расписания так, как
    // оно потом должно бы делаться само на основании данных из БД
    //=============================(1)===================================

    //Создаем коллекции чтобы было что вывести на экран

    //сопоставление номера пары в расписании и времени ее начала/окончания
    private val classNumberMap = mapOf<Int, CertainClassStartEndTime>(
        0 to CertainClassStartEndTime(0, "9:00", "10:25"),
        1 to CertainClassStartEndTime(1, "10:35", "12:00"),
        2 to CertainClassStartEndTime(2, "12:10", "13:35"),
        3 to CertainClassStartEndTime(3, "14:15", "15:40"),
        4 to CertainClassStartEndTime(4, "15:50", "17:15"),
        )

    val audienceList = listOf<String>("261.6", "261.17", "382", "266", "256", "230")

    val classNameList = listOf<String>(
        "Технологические машины и оборудование",
        "Операционные системы",
        "Математическая логика и теория алгоритмов",
        "Практическая подготовка по физической культуре и занятия спортом (элективные курсы)",
        "Иностранный язык",
        "Методы научных исследований")

    val classTypeMap = mapOf<Int, String>(
        0 to "Лекция",
        1 to "Практическое занятие",
        2 to "Лабораторная работа"
    )

    val classCount =  Random.nextInt(2,4)//количество пар, которое будет сгенерено

    fun addDataToScheduleDay(idScheduleDay: Int): MutableList<CertainClassInScheduleDay> {
        val listOfClasses = mutableListOf<CertainClassInScheduleDay>()

        for (i in (0.. classCount)){
            val classNumberId = i//classNumberMap[Random.nextInt(0,classNumberMap.size-1)]?.getId()
            val classAudienceId = Random.nextInt(0, audienceList.size-1)//audienceList.indexOf(audienceList[Random.nextInt(0,audienceList.size-1)])
            val classTypeId = Random.nextInt(0,classTypeMap.size-1)

            val className = Random.nextInt(0, classNameList.size-1)//[Random.nextInt(0, classNameList.size-1)]

            val newClass = CertainClassInScheduleDay(i, idScheduleDay, classNumberId, classAudienceId, classTypeId, className)
            listOfClasses.add(newClass)
        }
        return listOfClasses
    }

    val scheduleDay = addDataToScheduleDay(0)
    /*var b = classNumberMap[2]?.getStartClassTime()*/

    var b = "Info = ${scheduleDay[0].toString()}"


    /*val firstClass = CertainClassInScheduleDay(0,0,0,0,0,"Технологические машины и оборудование")*/

    //=============================(1)===================================


    //Реализация т.н. контроллера для того, чтобы генерировать виджеты расписания
    // в зависимости от этого самого расписания
    //=============================(2)===================================
    fun createSchedule(){
        for (i in scheduleDay){

            val view = layoutInflater.inflate(R.layout.certain_class_element, scheduleDay, false)
            view.apply {

            }
        }
    }


    //=============================(2)===================================

}