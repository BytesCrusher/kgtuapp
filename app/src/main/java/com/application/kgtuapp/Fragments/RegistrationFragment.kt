package com.application.kgtuapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {
    private lateinit var binding:FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)

        binding.bGoToAuthorisation.setOnClickListener {
            //Должно быть примерно так
            /*changeContentFragmentByRegistrationFragment(R.id.l_mainActivityFragment, AuthorisationFragment.newInstance())*/

            //а это костыль
            changeContentFragmentByRegistrationFragment(R.id.fc_contentContainer, AuthorisationFragment.newInstance())
        }
        return binding.root
    }

    private fun changeContentFragmentByRegistrationFragment(idContainer: Int, newFragment:Fragment){
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(idContainer, newFragment)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegistrationFragment()
    }
}