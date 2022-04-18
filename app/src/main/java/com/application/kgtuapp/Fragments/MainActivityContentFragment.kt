package com.application.kgtuapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.FragmentMainActivityContentBinding

class MainActivityContentFragment : Fragment() {
    lateinit var binding: FragmentMainActivityContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainActivityContentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    companion object {
        fun newInstance() = MainActivityContentFragment()
    }
}