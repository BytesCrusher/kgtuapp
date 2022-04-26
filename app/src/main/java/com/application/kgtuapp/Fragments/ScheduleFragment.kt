package com.application.kgtuapp.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.kgtuapp.Adapters.ScheduleRecyclerAdapter
import com.application.kgtuapp.Classes.CertainClassInScheduleDay
import com.application.kgtuapp.Classes.CertainClassStartEndTime
import com.application.kgtuapp.Classes.ScheduleTwoWeek
import com.application.kgtuapp.R
import com.application.kgtuapp.ViewModels.DataModel
import com.application.kgtuapp.databinding.FragmentScheduleBinding

class ScheduleFragment : Fragment(R.layout.fragment_schedule) {
    private lateinit var binding: FragmentScheduleBinding
    private val dataModel: DataModel by activityViewModels()

    var scheduleDataMap = mutableMapOf<Int, MutableList<CertainClassInScheduleDay>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleBinding.inflate(layoutInflater, container, false)

        binding.ibToolbarSettings.setOnClickListener {
            dataModel.studyGroup.value = null
            studyGroupNotSelected()
        }
        /*dataModel.mainToolBarTitle.value = getString(R.string.main_toolbar_description_schedule)*/

        //val recyclerView: RecyclerView = this.binding.scheduleDayContentContainer
        /*recyclerView.layoutManager = LinearLayoutManager(context)
        *//*recyclerView.adapter = ScheduleRecyclerAdapter(fillList("element"), R.id.tv_dayInfo, R.layout.item_certain_day)*//*
        recyclerView.adapter = ScheduleRecyclerAdapter(listOf(), R.id.b_selectStudyGroup, R.layout.item_study_group_not_selected, 5)*/


        if (dataModel.studyGroup.value != null) {
            dataModel.mainToolBarTitle.value =
                "${getString(R.string.main_toolbar_description_schedule)} ${dataModel.studyGroup.value}"
            binding.mainToolBar.title = dataModel.mainToolBarTitle.value

            //recyclerView.layoutManager = LinearLayoutManager(context)
            //recyclerView.adapter = ScheduleRecyclerAdapter(fillList("element"))

            val schedule = ScheduleTwoWeek()
            if (scheduleDataMap.isEmpty()) {
                scheduleDataMap = schedule.createScheduleTwoWeek()
            }

            createSchedule(scheduleDataMap)
            /*val recyclerViewLayout = layoutInflater.inflate(
                R.layout.item_schedule, binding.llScheduleContentContainer, false)
            recyclerViewLayout.apply {
                val recyclerView: RecyclerView = this.findViewById<RecyclerView>(R.id.scheduleDayContentContainer)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = ScheduleRecyclerAdapter(scheduleDataMap, listOf(), R.id.tv_dayInfo, R.layout.item_certain_day, 15)
            }
            binding.llScheduleContentContainer.addView(recyclerViewLayout)*/
            //
        } else {
            studyGroupNotSelected()
        }
        return binding.root
    }

    private fun fillList(element: String): List<String> {
        val data = mutableListOf<String>()
        (0..30).forEach { i -> data.add("$i $element") }
        return data
    }

    //когда группа не выбрана отобразить соответствующее окно
    private fun studyGroupNotSelected() {
        //обнуляем что есть
        binding.llScheduleContentContainer.removeAllViews()
        dataModel.mainToolBarTitle.value = getString(R.string.main_toolbar_description_schedule)
        binding.mainToolBar.title = dataModel.mainToolBarTitle.value
        scheduleDataMap = mutableMapOf<Int, MutableList<CertainClassInScheduleDay>>()

        //recyclerView.layoutManager = LinearLayoutManager(context)
        //recyclerView.adapter = ScheduleRecyclerAdapter(listOf())

        val studyGroupNotChoosed = layoutInflater.inflate(
            R.layout.item_study_group_not_selected,
            binding.llScheduleContentContainer,
            true
        )
        studyGroupNotChoosed.apply {
            val b_selectStudyGroup = this.findViewById<Button>(R.id.b_selectStudyGroup)
            b_selectStudyGroup.setOnClickListener {
                changeContentFragmentByScheduleFragment(
                    R.id.l_mainActivityFragment,
                    ScheduleChooseGroupFragment.newInstance()
                )
            }
        }
        /*binding.llScheduleContentContainer.addView(studyGroupNotChoosed)*/
    }

    private fun changeContentFragmentByScheduleFragment(idContainer: Int, newFragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(idContainer, newFragment)
            .commit()
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
        5 to CertainClassStartEndTime(5, "17:25", "18:50"),
        6 to CertainClassStartEndTime(6, "19:00", "20:25"),
    )

    /*val classNameList = listOf<String>(
        "Технологические машины и оборудование",
        "Операционные системы",
        "Математическая логика и теория алгоритмов",
        "Практическая подготовка по физической культуре и занятия спортом (элективные курсы)",
        "Иностранный язык",
        "Методы научных исследований"
    )*/

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
    private fun createSchedule(scheduleMap: MutableMap<Int, MutableList<CertainClassInScheduleDay>>) {
        var n = 0

        for (day in 0..13) {
            //название нового дна
            val daysList = mutableListOf<View>()
            val newDay = layoutInflater.inflate(
                R.layout.item_certain_day,
                binding.llScheduleContentContainer,
                false
            )
            newDay.apply {
                this.id = day
                /*val tv_dayInfo = this.findViewById<TextView>(R.id.tv_dayInfo)
                tv_dayInfo.text = ""*/
            }
            daysList.add(newDay)
            binding.llScheduleContentContainer.addView(newDay)

            /*binding.scheduleDayContentContainer.findViewById<ViewGroup>(R.id)*/
            val dayContainer = newDay.findViewById<LinearLayout>(R.id.ll_certainDayItemContainer)
            n = 0
            val todayClassisEmpty = scheduleMap[day]?.isEmpty()
            /*?.size ?: 0*/
            if (todayClassisEmpty == true) {
                //if (todayClassCount == 0) {
                    val view = layoutInflater.inflate(
                        R.layout.item_today_is_a_day_off,
                        dayContainer,
                        true
                    )
                    //dayContainer.addView(view)
                //}
            } else {
                for (key in 0 until (scheduleMap[day]?.size ?: 0)) {
                    val view = layoutInflater.inflate(
                        R.layout.item_certain_class,
                        dayContainer,
                        false
                    )
                    view.apply {
                        this.id = day * 100 + n

                        //далее идут варианты как можно вывести на экран данные
                        //вывести реальное значение, после комментария айдишник
                        //здесь используется устаревший findViewById, по факту так делать уже нельзя,
                        // нужно каким-то образом все равно мигрировать на binding, как я понял

                        val tv_class_number = this.findViewById<TextView>(R.id.tv_class_number)
                        tv_class_number.text =
                            (scheduleMap[day]!![key].idClassNumber?.plus(1)).toString()//n.toString()

                        val tv_class_name = this.findViewById<TextView>(R.id.tv_class_name)
                        tv_class_name.text =
                            scheduleMap[day]!![key].idClassName

                        val tv_class_start_time =
                            this.findViewById<TextView>(R.id.tv_class_start_time)
                        tv_class_start_time.text =
                            classNumberMap[scheduleMap[day]!![key].idClassNumber!!]?.getStartClassTime()

                        val tv_class_type_name =
                            this.findViewById<TextView>(R.id.tv_class_type_name)
                        tv_class_type_name.text = classTypeMap[scheduleMap[day]!![key].idClassType]


                        when (tv_class_type_name.text.toString()){
                            "практические"->tv_class_type_name
                                .setTextColor(AppCompatResources.getColorStateList(
                                    context,
                                    R.color.scheduleClassTypePracticeText))
                            "лабораторные" -> tv_class_type_name
                                .setTextColor(AppCompatResources.getColorStateList(
                                    context,
                                    R.color.scheduleClassTypeLaboratoryWorkText))
                        }

                        val b_audience = this.findViewById<Button>(R.id.b_audience)
                        b_audience.text = audienceList[scheduleMap[day]!![key].idClassAudience]
                        b_audience.setOnClickListener {
                            dayContainer.removeView(this)
                        }
                    }
                    dayContainer.addView(view)
                    n += 1
                }
            }
        }
    }
    //=============================(2)===================================
}