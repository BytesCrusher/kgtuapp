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
        /*binding.textView2.text= b*/
        //конец костылей

        createScheduleTwoWeek()

        createSchedule()
        return binding.root
    }

    //этого здесь быть не должно
    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createSchedule()
    }*/

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

    val audienceList = listOf<String>("261.6", "261.17", "382", "266", "256", "230")

    fun addDataToScheduleDay(idScheduleDay: Int): MutableList<CertainClassInScheduleDay> {
        val listOfClasses = mutableListOf<CertainClassInScheduleDay>()

        val classCount =  Random.nextInt(1,5)//количество пар, которое будет сгенерено

        for (i in (0.. classCount)){
            val classNumberId = i//classNumberMap[Random.nextInt(0,classNumberMap.size-1)]?.getId()
            val classAudienceId = Random.nextInt(0, audienceList.size)//audienceList.indexOf(audienceList[Random.nextInt(0,audienceList.size-1)])
            val classTypeId = Random.nextInt(0,classTypeMap.size)

            val className = Random.nextInt(0, classNameList.size)//[Random.nextInt(0, classNameList.size-1)]

            val newClass = CertainClassInScheduleDay(i, idScheduleDay, classNumberId, classAudienceId, classTypeId, className)
            listOfClasses.add(newClass)
        }
        return listOfClasses
    }

    /*MutableList<CertainClassInScheduleDay>*/

    val scheduleMap = mutableMapOf<Int, MutableList<CertainClassInScheduleDay>>()

    fun createScheduleTwoWeek(){
        for (i in 0.. 13){
            val scheduleDay = addDataToScheduleDay(i)
            scheduleMap[i] = scheduleDay
        }
    }

    /*val firstClass = CertainClassInScheduleDay(0,0,0,0,0,"Технологические машины и оборудование")*/

    //=============================(1)===================================


    //Реализация т.н. контроллера для того, чтобы генерировать виджеты расписания
    // в зависимости от этого самого расписания
    //=============================(2)===================================


    fun createSchedule(){
        var n = 0
        /*val viewList = mutableListOf<View>()*/

        for (day in 0.. 13) {
            //название нового дна
            val newDay = layoutInflater.inflate(R.layout.certain_day_element, binding.scheduleDayContentContainer, false)
            binding.scheduleDayContentContainer.addView(newDay)

            n=0
            for (key in 0..(scheduleMap[day]?.size ?: 5) -1) {
                val view = layoutInflater.inflate(
                    R.layout.certain_class_element,
                    binding.scheduleDayContentContainer,
                    false
                )
                /*val view2 = layoutInflater.inflate(R.layout.certain_class_element, binding.root)*/
                view.apply {
                    this.id = day*100 + n

                    //далее идут варианты как можно вывести на экран данные
                    //вывести реальное значение, после комментария айдишник
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
                /*binding.scheduleDayContentContainer.setVerticalGravity(1)*/
                binding.scheduleDayContentContainer.addView(view)

                n += 1
            }
        }

        /*n=0
        for (i in scheduleDay){
            val view = layoutInflater.inflate(R.layout.certain_class_element, binding.scheduleDayContentContainer, false)
            view.apply {
                this.id = n
                val tv_class_number = this.findViewById<TextView>(R.id.tv_class_number)
                tv_class_number.text = n.toString()

                val tv_class_name = this.findViewById<TextView>(R.id.tv_class_name)
                tv_class_name.text = scheduleDay[n].idClassName.toString()

                val audience_button = this.findViewById<Button>(R.id.b_audience)
                audience_button.setOnClickListener { binding.scheduleDayContentContainer.removeView(this) }

            }
            binding.scheduleDayContentContainer.setVerticalGravity(1)
            binding.scheduleDayContentContainer.addView(view)

            n += 1
        }*/

        /*val view2 = layoutInflater.inflate(R.layout.certain_class_element, binding.scheduleDayContentContainer, false)

        view2.apply {
            val tv_class_name = this.findViewById<TextView>(R.id.tv_class_start_time)
            tv_class_name.text = "rhehrdrdhshshfhfsdfhsf"
        }
        binding.scheduleDayContentContainer.addView(view2)*/
    }


    //=============================(2)===================================

}