package com.application.kgtuapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.FragmentUniversityBinding

class UniversityFragment : Fragment(R.layout.fragment_university) {
    private lateinit var binding: FragmentUniversityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUniversityBinding.inflate(layoutInflater, container, false)

        val view = layoutInflater.inflate(
            R.layout.item_not_done_yet,
            binding.contentContainerFragmentUniversity,
            false
        )
        binding.contentContainerFragmentUniversity.addView(view)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = UniversityFragment()
    }
}