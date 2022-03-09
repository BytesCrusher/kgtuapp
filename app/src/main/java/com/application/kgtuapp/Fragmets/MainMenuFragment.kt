package com.application.kgtuapp.Fragmets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.application.kgtuapp.MainActivity
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.FragmentMainMenuBinding

class MainMenuFragment:Fragment(R.layout.fragment_main_menu) {
    private lateinit var binding: FragmentMainMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*binding.mainMenuButtonSchedule.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.contentLayout, ScheduleDayFragment.newInstance())
                .commit()
        }*/
        /*binding.mainMenuButtonCalendar.setOnClickListener {  }
        binding.mainMenuButtonUniversity.setOnClickListener {  }
        binding.mainMenuButtonPerson.setOnClickListener {  }*/

        binding = FragmentMainMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance() = MainMenuFragment()
    }

}