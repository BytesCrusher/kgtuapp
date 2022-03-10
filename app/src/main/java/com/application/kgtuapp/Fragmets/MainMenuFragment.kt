package com.application.kgtuapp.Fragmets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.FragmentMainMenuBinding

class MainMenuFragment:Fragment(R.layout.fragment_main_menu) {
    private lateinit var binding: FragmentMainMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMenuBinding.inflate(layoutInflater, container, false)

        binding.mainMenuButtonSchedule.setOnClickListener {
            changeContentFragmentByMainMenu(R.id.contentContainer, ScheduleDayFragment.newInstance())
        }
        binding.mainMenuButtonCalendar.setOnClickListener{
            changeContentFragmentByMainMenu(R.id.contentContainer, PersonalCalendarFragment.newInstance())
        }
        binding.mainMenuButtonUniversity.setOnClickListener {
            changeContentFragmentByMainMenu(R.id.contentContainer, UniversityFragment.newInstance())
        }
        binding.mainMenuButtonPerson.setOnClickListener {
            changeContentFragmentByMainMenu(R.id.contentContainer, ProfileFragment.newInstance())
        }

        return binding.root
    }

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