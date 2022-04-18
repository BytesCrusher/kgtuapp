package com.application.kgtuapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.application.kgtuapp.R
import com.application.kgtuapp.ViewModels.DataModel
import com.application.kgtuapp.databinding.FragmentMainMenuBinding

class MainMenuFragment:Fragment(R.layout.fragment_main_menu) {
    private lateinit var binding: FragmentMainMenuBinding
    //бесполезный коммент

    private val dataModel : DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMenuBinding.inflate(layoutInflater, container, false)
        /*dataModel.mainToolBarTitle.value = getString(R.string.main_toolbar_description_schedule)*/

        //показывает кружочек типо есть оповещение
        /*var badge = binding.bottomNavigation.getOrCreateBadge(R.id.mainMenuButtonSchedule)
        badge.isVisible = true
        badge.number = 1*/

        //этот код позволит скрыть badge
        /*if (badge != null) {
            badge.isVisible = false
            badge.clearNumber()
        }*/


        //установить сейчас используемый пункт меню на расписание
        binding.bottomNavigation.selectedItemId = R.id.mainMenuButtonSchedule

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.mainMenuButtonSchedule -> {
                    changeContentFragmentByMainMenu(R.id.fc_contentContainer, ScheduleFragment.newInstance())
                    dataModel.mainToolBarTitle.value = getString(R.string.main_toolbar_description_schedule)
                    true
                }
                R.id.mainMenuButtonNavigator -> {
                    changeContentFragmentByMainMenu(R.id.fc_contentContainer, PersonalCalendarFragment.newInstance())
                    dataModel.mainToolBarTitle.value = getString(R.string.main_toolbar_description_navigator)
                    true
                }
                R.id.mainMenuButtonUniversity -> {
                    changeContentFragmentByMainMenu(R.id.fc_contentContainer, UniversityFragment.newInstance())
                    dataModel.mainToolBarTitle.value = getString(R.string.main_toolbar_description_university)
                    true
                }
                R.id.mainMenuButtonPerson -> {
                    /*if (dataModel.studyGroup.value == null){
                        changeContentFragmentByMainMenu(R.id.contentContainer, ProfileFragment.newInstance())
                    } else {
                        changeContentFragmentByMainMenu(R.id.contentContainer, InfoFragment.newInstance())
                    }*/
                    changeContentFragmentByMainMenu(R.id.fc_contentContainer, ProfileFragment.newInstance())
                    dataModel.mainToolBarTitle.value = getString(R.string.main_toolbar_description_profile)
                    true
                }
                else -> false
            }

        }

        return binding.root
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.checkTextButton.setOnClickListener{
            dataModel.message.value = "hello from info Fragment"
        }

    }*/

    //Поменять текущий фрагмент внутри contentContainer
    //(фрагмент для отображения основного контента)
    //Аргументы:
    //(R.id.contentContainer, ScheduleDayFragment.newInstance())
    private fun changeContentFragmentByMainMenu(idContainer: Int, newFragment:Fragment){
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(idContainer, newFragment)
            .commit()
    }


    companion object {
        @JvmStatic
        fun newInstance() = MainMenuFragment()
    }

}