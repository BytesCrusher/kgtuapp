package com.application.kgtuapp.Fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.application.kgtuapp.R
import com.application.kgtuapp.ViewModels.DataModel
import com.application.kgtuapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private val dataModel: DataModel by viewModels()

    /*@RequiresApi(Build.VERSION_CODES.N)*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        //Это отображение учебной группы в разделе "Профиль"
        // почему-то всегда выдает null, надо фиксить
        binding.tvScheduleUserStudyGroupBody.setText(dataModel.studyGroup.value.toString())

        //не работает
        dataModel.studyGroup.observe(viewLifecycleOwner, {
            binding.tvScheduleUserStudyGroupBody.text = it
        })

        /*update()*/
        this.binding.bProfileSettings.setOnClickListener {
            binding.tvScheduleUserStudyGroupBody.text = dataModel.studyGroup.value.toString()
        }
        return binding.root
    }

    fun update(){
        this.binding.tvScheduleUserStudyGroupBody.text = dataModel.studyGroup.value

        /*dataModel.studyGroup.observe(viewLifecycleOwner, {
            binding.tvScheduleUserStudyGroupBody.text = it
        })*/
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
