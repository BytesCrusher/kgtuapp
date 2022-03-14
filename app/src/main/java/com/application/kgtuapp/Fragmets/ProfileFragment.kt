package com.application.kgtuapp.Fragmets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.FragmentProfileBinding
import com.application.kgtuapp.databinding.FragmentScheduleBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        /*MaterialAlertDialogBuilder(context)
        Да? Пизда!
            // Add customization options here
            .show()*/
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
