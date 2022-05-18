package com.application.kgtuapp.screens.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.kgtuapp.BuildConfig
import com.application.kgtuapp.R
import com.application.kgtuapp.screens.schedule.ScheduleFragment
import com.application.kgtuapp.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {
    private lateinit var binding: FragmentNotificationsBinding

    //private val viewModel: ScheduleChooseGroupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(layoutInflater, container, false)
        binding.ibToolbarGoBack.setOnClickListener {
            changeContentFragmentByNotificationsFragment(
                R.id.l_mainActivityFragment,
                ScheduleFragment.newInstance()
            )
        }

        binding.tvApkVersion.text = """
            Вариант сборки = ${BuildConfig.BUILD_TYPE}            
            Версия приложения = ${BuildConfig.VERSION_NAME}
            Код версии приложения = ${BuildConfig.VERSION_CODE}
        """//.trimIndent()

        /*viewModel.search()
        viewModel.institutesDataListLiveData.observe(viewLifecycleOwner){
            it.forEach {
                binding.tvApkVersion.text = "${binding.tvApkVersion.text} + ${it.id} + ${it.instituteName} ||"
            }
        }*/
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotificationsFragment()
    }

    private fun changeContentFragmentByNotificationsFragment(idContainer: Int, newFragment:Fragment){
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(idContainer, newFragment)
            .commit()
    }
}