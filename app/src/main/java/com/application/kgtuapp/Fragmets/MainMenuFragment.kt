package com.application.kgtuapp.Fragmets

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

    private val dataModel : DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMenuBinding.inflate(layoutInflater, container, false)

        binding.mainMenuButtonSchedule.setOnClickListener {
            changeContentFragmentByMainMenu(R.id.contentContainer, ScheduleFragment.newInstance())
            dataModel.mainToolBarTitle.value = getString(R.string.main_toolbar_description_schedule)
            /*binding.mainMenuButtonSchedule.setBackgroundResource(R.color.choosedButtonOnBottomNavBar)*/
        }
        binding.mainMenuButtonCalendar.setOnClickListener{
            changeContentFragmentByMainMenu(R.id.contentContainer, PersonalCalendarFragment.newInstance())
            dataModel.mainToolBarTitle.value = getString(R.string.main_toolbar_description_navigator)
            /*binding.mainMenuButtonCalendar.setBackgroundResource(R.color.choosedButtonOnBottomNavBar)*/
        }
        binding.mainMenuButtonUniversity.setOnClickListener {
            changeContentFragmentByMainMenu(R.id.contentContainer, UniversityFragment.newInstance())
            dataModel.mainToolBarTitle.value = getString(R.string.main_toolbar_description_university)
            /*binding.mainMenuButtonUniversity.setBackgroundResource(R.color.choosedButtonOnBottomNavBar)*/
        }
        binding.mainMenuButtonPerson.setOnClickListener {
            changeContentFragmentByMainMenu(R.id.contentContainer, ProfileFragment.newInstance())
            dataModel.mainToolBarTitle.value = getString(R.string.main_toolbar_description_profile)
            /*binding.mainMenuButtonPerson.setBackgroundResource(R.color.choosedButtonOnBottomNavBar)*/
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