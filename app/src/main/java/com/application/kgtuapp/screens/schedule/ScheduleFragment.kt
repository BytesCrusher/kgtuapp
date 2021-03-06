package com.application.kgtuapp.screens.schedule

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.application.kgtuapp.Network.Network
import com.application.kgtuapp.screens.notifications.NotificationFragment
import com.application.kgtuapp.screens.scheduleChooseGroup.ScheduleChooseGroupFragment
import com.application.kgtuapp.R
import com.application.kgtuapp.ViewModels.DataModel
import com.application.kgtuapp.databinding.FragmentScheduleBinding
import com.application.kgtuapp.screens.notifications.NotificationsListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScheduleFragment : Fragment(R.layout.fragment_schedule) {
    private lateinit var binding: FragmentScheduleBinding
    private val dataModel: DataModel by activityViewModels()

    //добавили вот эту вью модель
    private val viewModel: ScheduleViewModel by viewModels()

    private val sharedPrefs by lazy{
        requireContext().getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)}

    var scheduleDataMap = mutableMapOf<Int, MutableList<CertainClassInScheduleDay>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScheduleBinding.inflate(layoutInflater, container, false)

        val studyGroupId = checkStudyGroupIdFromPreferences()

       /* binding.topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.notifications -> {
                    changeContentFragmentByScheduleFragment(
                        R.id.l_mainActivityFragment,
                        NotificationFragment.newInstance()
                    )
                    true
                }
                R.id.settings -> {
                    dataModel.studyGroup.value = null
                    saveStudyGroupToPreferences(null)
                    studyGroupNotSelected()
                    true
                }
                *//*R.id.more -> {
                    // Handle more item (inside overflow menu) press
                    true
                }*//*
                else -> false
            }
        }*/

        binding.ibToolbarNotifications.setOnClickListener {
            changeContentFragmentByScheduleFragment(
                R.id.l_mainActivityFragment,
                NotificationsListFragment.newInstance()
            )
        }

        binding.ibToolbarSettings.setOnClickListener {
            dataModel.studyGroup.value = null
            saveStudyGroupToPreferences(null)
            studyGroupNotSelected()
        }
        dataModel.mainToolBarTitle.value = getString(R.string.main_toolbar_description_schedule)

        //val recyclerView: RecyclerView = this.binding.scheduleDayContentContainer
        /*recyclerView.layoutManager = LinearLayoutManager(context)
        *//*recyclerView.adapter = ScheduleRecyclerAdapter(fillList("element"), R.id.tv_dayInfo, R.layout.item_certain_day)*//*
        recyclerView.adapter = ScheduleRecyclerAdapter(listOf(), R.id.b_selectStudyGroup, R.layout.item_study_group_not_selected, 5)*/

        /*val a = checkStudyGroupFromPreferences()
        dataModel.studyGroup.value = a*/

        //я не знаю почему это работает, в интернете пишут что надо указывать:
        // lifecycleScope.launch(Dispatchers.IO) {
        dataModel.studyGroup.value = sharedPrefs.getString(USER_STUDY_GROUP, null)
        //dataModel.studyGroup.value = "20-АП"
        if (dataModel.studyGroup.value != null) {

            //invokeCriticalErrorByScheduleFragment()

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

        //вызов к апи
        sendPostRequest()

        return binding.root
    }

    private fun sendPostRequest(){
        if (checkInternetConnection() == true){
            viewModel.sendPostRequest()
        } else {
            showThisInNoInternetConnection()
        }
    }

    private fun checkInternetConnection(): Boolean? {
        return context?.let { Network.isOnline(it.applicationContext) }
    }

    private fun showThisInNoInternetConnection(){
        Log.i("Internet",
            "There are no internet connection\n" +
                    "Network.isOnline = ${context?.let { Network.isOnline(it.applicationContext) }}")

        binding.llScheduleContentContainer.removeAllViews()
        layoutInflater.inflate(
            R.layout.item_no_internet_connection,
            binding.llScheduleContentContainer,
            true
        )
    }

    //метод должен быть какой-то такой, но чет не получается
    // его реализовать в отдельном потоке
    private fun checkStudyGroupIdFromPreferences(): Int{
        var userStudyGroup: String? = null
        var userStudyGroupID: Int = 0
        lifecycleScope.launch(Dispatchers.IO) {
            userStudyGroup = sharedPrefs.getString(USER_STUDY_GROUP, null)
            userStudyGroupID = sharedPrefs.getInt(USER_STUDY_GROUP_ID, 0)
        }
        return userStudyGroupID
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
        //binding.mainToolBar.title = dataModel.mainToolBarTitle.value
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

    private fun saveStudyGroupToPreferences(data: String?){
        dataModel.studyGroup.value = data
        lifecycleScope.launch(Dispatchers.IO) {
            sharedPrefs.edit()
                .putString(USER_STUDY_GROUP, data)
                .apply()
            sharedPrefs.edit()
                .putString(USER_STUDY_GROUP_ID, data)
                .apply()
        }
    }

    /*private fun invokeCriticalErrorByScheduleFragment() {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setView(R.layout.item_critical_error_schedule)
                .setPositiveButton(resources.getString(R.string.schedule_critical_error_b_reload)) { dialog, which ->
                    exitProcess(0)
                }
                .show()
        }
    }*/


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
        private const val SHARED_PREFS_NAME = "user_data_shared_prefs"
        private const val USER_STUDY_GROUP = "user study group"
        private const val USER_STUDY_GROUP_ID = "user study group id"
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

    val daysCalendarList = listOf<String>(
        "Пятница, 29 апреля",
        "Суббота, 30 апреля",
        "Воскресенье, 1 мая",
        "Понедельник, 2 мая",
        "Вторник, 3 мая",
        "Среда, 4 мая",
        "Четверг, 5 мая",
        "Пятница, 6 мая",
        "Суббота, 7 мая",
        "Воскресенье, 8 мая",
        "Понедельник, 9 мая",
        "Вторник, 10 мая",
        "Среда, 11 мая",
        "Четверг, 12 мая"
    )

    val classTypeMap = mapOf<Int, String>(
        0 to "лекции",
        1 to "практические",
        2 to "лабораторные",
        3 to "экзамен"
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
                val textView = this.findViewById<TextView>(R.id.tv_dayInfo)
                textView.text = daysCalendarList[day]

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
                            "экзамен" -> tv_class_type_name
                                .setTextColor(AppCompatResources.getColorStateList(
                                    context,
                                    R.color.scheduleClassTypeExamen))
                        }

                        val b_audience = this.findViewById<Button>(R.id.b_audience)
                        b_audience.text = audienceList[scheduleMap[day]!![key].idClassAudience]
                        /*b_audience.setOnClickListener {
                            dayContainer.removeView(this)
                        }*/
                    }
                    dayContainer.addView(view)
                    n += 1
                }
            }
        }
    }
    //=============================(2)===================================
}