package com.application.kgtuapp.Fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.kgtuapp.R
import com.application.kgtuapp.screens.schedule.ScheduleFragment
import com.application.kgtuapp.databinding.FragmentAutorizationBinding

class AuthorisationFragment : Fragment() {
    private lateinit var binding: FragmentAutorizationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAutorizationBinding.inflate(layoutInflater, container, false)

        binding.bGoToRegistration.setOnClickListener {
            /*changeContentFragmentByAuthorisationFragment(R.id.l_mainActivityFragment, RegistrationFragment.newInstance())*/

            //костыль
            changeContentFragmentByAuthorisationFragment(R.id.l_mainActivityFragment, RegistrationFragment.newInstance())
        }

        binding.bEnterToApp.setOnClickListener {
            //Это все костыль костылей велосипедов
            // Устаревший подход, надо переписать
            changeContentFragmentByAuthorisationFragment(R.id.l_mainActivityFragment, InfoFragment.newInstance())
            val handler = Handler()
            handler.postDelayed({
                // do something after 2000ms
                changeContentFragmentByAuthorisationFragment(R.id.l_mainActivityFragment, ScheduleFragment.newInstance())
            }, 2000)


            /*Toast.makeText(this, "Нажата кнопка", Toast.LENGTH_SHORT).show()*/

            //здесь надо допилить показ main activity
            //скорее всего надо класть весь main activity во фрагмент, но я пока не уверен, что это првильное решение
            //фантазия на тему:
            /*changeContentFragmentByAuthorisationFragment(R.id.l_mainActivityFragment, MainActivity.newInstance())*/
        }
        return binding.root
    }

    private fun changeContentFragmentByAuthorisationFragment(idContainer: Int, newFragment:Fragment){
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(idContainer, newFragment)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthorisationFragment()
    }
}