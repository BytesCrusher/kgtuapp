package com.application.kgtuapp.Fragmets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.FragmentScheduleChooseGroupBinding

class ScheduleChooseGroupFragment : Fragment(R.layout.fragment_schedule_choose_group) {
    private lateinit var binding: FragmentScheduleChooseGroupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleChooseGroupBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = ScheduleChooseGroupFragment()
    }
}