package com.application.kgtuapp.Fragmets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.FragmentPersonalCalendarBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PersonalCalendarFragment : Fragment(R.layout.fragment_personal_calendar) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentPersonalCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalCalendarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = PersonalCalendarFragment()
    }
}