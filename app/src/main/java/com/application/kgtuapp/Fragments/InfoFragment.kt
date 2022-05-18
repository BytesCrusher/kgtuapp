package com.application.kgtuapp.Fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.application.kgtuapp.R
import com.application.kgtuapp.ViewModels.DataModel
import com.application.kgtuapp.databinding.FragmentInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment: Fragment(R.layout.fragment_info) {
    lateinit var binding: FragmentInfoBinding
    private val dataModel : DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(layoutInflater)

        dataModel.mainToolBarTitle.value = getString(R.string.main_toolbar_description_schedule)
        dataModel.studyGroup.value = null

        //Устаревший подход, надо переписать
        val handler = Handler()
        handler.postDelayed({
            changeContentFragmentByInfoFragment(R.id.l_mainActivityFragment, ScheduleFragment.newInstance())
        }, 2000)

        return binding.root
    }

    private fun changeContentFragmentByInfoFragment(idContainer: Int, newFragment:Fragment){
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(idContainer, newFragment)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = InfoFragment()
    }
}