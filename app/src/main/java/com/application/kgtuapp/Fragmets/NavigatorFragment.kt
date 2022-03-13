package com.application.kgtuapp.Fragmets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.FragmentNavigatorBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PersonalCalendarFragment : Fragment(R.layout.fragment_navigator) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentNavigatorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavigatorBinding.inflate(layoutInflater, container, false)

        val view = layoutInflater.inflate(
            R.layout.item_not_done_yet,
            binding.navigatorFragmentContentContainer,
            false
        )
        binding.navigatorFragmentContentContainer.addView(view)

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = PersonalCalendarFragment()
    }
}