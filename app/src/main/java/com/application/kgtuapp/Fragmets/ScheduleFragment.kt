package com.application.kgtuapp.Fragmets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.application.kgtuapp.Classes.CertainClassInScheduleDay
import com.application.kgtuapp.Classes.CertainClassStartEndTime
import com.application.kgtuapp.Classes.ScheduleTwoWeek
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.FragmentScheduleBinding

class ScheduleFragment : Fragment(R.layout.fragment_schedule) {
    private lateinit var binding: FragmentScheduleBinding
    //этот коммент надо удалить

    var scheduleDataMap = mutableMapOf<Int, MutableList<CertainClassInScheduleDay>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleBinding.inflate(layoutInflater, container, false)
        val schedule = ScheduleTwoWeek()
        scheduleDataMap = schedule.createScheduleTwoWeek()

        createSchedule(scheduleDataMap)

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ScheduleFragment()
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

    val classNameList = listOf<String>(
        "Технологические машины и оборудование",
        "Операционные системы",
        "Математическая логика и теория алгоритмов",
        "Практическая подготовка по физической культуре и занятия спортом (элективные курсы)",
        "Иностранный язык",
        "Методы научных исследований")

    val classTypeMap = mapOf<Int, String>(
        0 to "лекции",
        1 to "практические",
        2 to "лабораторные"
    )

    val audienceList = listOf<String>("261.6", "261.17", "382", "266", "256", "230")
    //=============================(1)===================================

    //Реализация т.н. контроллера для того, чтобы генерировать виджеты расписания
    // в зависимости от этого самого расписания
    //=============================(2)===================================
    private fun createSchedule(scheduleMap: MutableMap<Int, MutableList<CertainClassInScheduleDay>>){
        var n = 0

        for (day in 0.. 13) {
            //название нового дна
                // здесь есть косяк. Сейчас перед каждым списком пар выводится item
                    // в котором лежит дата и название дня. На самом деле сам список пар и
                        // этот заголовок дня должны лежать в одном контейнере (linerLayout-е)

            val newDay = layoutInflater.inflate(R.layout.certain_day_element, binding.scheduleDayContentContainer, false)
            newDay.apply {
                /*val tv_dayInfo = this.findViewById<TextView>(R.id.tv_dayInfo)
                tv_dayInfo.text = ""*/
            }
            binding.scheduleDayContentContainer.addView(newDay)

            n=0
            for (key in 0..(scheduleMap[day]?.size ?: 5) -1) {
                val view = layoutInflater.inflate(
                    R.layout.certain_class_element,
                    binding.scheduleDayContentContainer,
                    false
                )
                view.apply {
                    this.id = day*100 + n

                    //далее идут варианты как можно вывести на экран данные
                    //вывести реальное значение, после комментария айдишник
                    //здесь используется устаревший findViewById, по факту так делать уже нельзя,
                    // нужно каким-то образом все равно мигрировать на binding, как я понял

                    val tv_class_number = this.findViewById<TextView>(R.id.tv_class_number)
                    tv_class_number.text = (scheduleMap[day]!![key].idClassNumber?.plus(1)).toString()//n.toString()

                    val tv_class_name = this.findViewById<TextView>(R.id.tv_class_name)
                    tv_class_name.text = classNameList[scheduleMap[day]!![key].idClassName]//scheduleMap[day]?.get(key)?.idClassName.toString()

                    val tv_class_start_time = this.findViewById<TextView>(R.id.tv_class_start_time)
                    tv_class_start_time.text = classNumberMap[scheduleMap[day]!![key].idClassNumber!!]!!.getStartClassTime()

                    val tv_class_type_name = this.findViewById<TextView>(R.id.tv_class_type_name)
                    tv_class_type_name.text =  classTypeMap[scheduleMap[day]!![key].idClassType]

                    val b_audience = this.findViewById<Button>(R.id.b_audience)
                    b_audience.text = audienceList[scheduleMap[day]!![key].idClassAudience]
                    b_audience.setOnClickListener {
                        binding.scheduleDayContentContainer.removeView(this)
                    }
                }
                binding.scheduleDayContentContainer.addView(view)
                n += 1
            }
        }
    }
    //=============================(2)===================================
}