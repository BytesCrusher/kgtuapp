package com.application.kgtuapp.Fragmets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.application.kgtuapp.R
import com.application.kgtuapp.ViewModels.DataModel
import com.application.kgtuapp.databinding.FragmentInfoBinding

class InfoFragment: Fragment(R.layout.fragment_info) {
    lateinit var binding: FragmentInfoBinding
    private val dataModel : DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(layoutInflater)
        dataModel.mainToolBarTitle.value = getString(R.string.app_name)
        return binding.root
    }

    //Отправка с помощью view модели информации из фрагмента в активити
    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.checkTextButton.setOnClickListener{
            dataModel.mainToolBarTitle.value = "hello from info Fragment"
        }
    }*/

    companion object {
        @JvmStatic
        fun newInstance() = InfoFragment()
    }

}