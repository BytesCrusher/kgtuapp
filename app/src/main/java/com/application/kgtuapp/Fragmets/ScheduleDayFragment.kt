package com.application.kgtuapp.Fragmets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.kgtuapp.R

/**
 * A simple [Fragment] subclass.
 * Use the [ScheduleDayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScheduleDayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_day, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ScheduleDayFragment()
    }
}