package com.application.kgtuapp.screens.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.application.kgtuapp.BuildConfig
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding

    private val viewModel: NotificationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(layoutInflater, container, false)

        binding.ibToolbarGoBack.setOnClickListener {
            changeContentFragmentByNotificationFragment(
                R.id.l_mainActivityFragment,
                NotificationsListFragment.newInstance()
            )
        }

        with(viewModel.notificationDataLiveData.value){

        }

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
        fun newInstance() = NotificationFragment()
    }

    private fun changeContentFragmentByNotificationFragment(idContainer: Int, newFragment:Fragment){
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(idContainer, newFragment)
            .commit()
    }
}