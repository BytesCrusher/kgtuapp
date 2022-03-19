package com.application.kgtuapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.application.kgtuapp.R
import com.application.kgtuapp.ViewModels.DataModel
import com.application.kgtuapp.databinding.FragmentScheduleChooseGroupBinding
import com.google.android.material.chip.Chip

class ScheduleChooseGroupFragment : Fragment(R.layout.fragment_schedule_choose_group) {
    private lateinit var binding: FragmentScheduleChooseGroupBinding
    private val dataModel : DataModel by activityViewModels()

    val groupList = listOf<String>("18-ВТ", "20-АП", "20-МС", "20-ВТ", "20-КС", "19-ИЭ", "19-АП", "19-ВТ")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleChooseGroupBinding.inflate(layoutInflater, container, false)
        /*scheduleChooseGroupChipGroup*/

        //Поисковик по группам
        binding.etGroupSearch.addTextChangedListener {
            binding.scheduleChooseGroupChipGroup.removeAllViews()
            binding.tvSelectGroupAllGroups.visibility = VISIBLE
            var viewCount = 0
            for (i in 0..groupList.size-1){
                if (binding.etGroupSearch.text.toString() in groupList[i]){
                    viewCount+=1
                    val chip = Chip(context)
                    chip.apply {
                        this.id = i
                        this.text = groupList[i]
                        /*this.style="@style/Widget.Material3.Chip.Suggestion.Elevated"*/
                        this.setOnClickListener{
                            dataModel.studyGroup.value = groupList[i]
                            dataModel.mainToolBarTitle.value = getString(R.string.main_toolbar_description_schedule)
                            changeContentFragmentByChooseGroupFragment(R.id.contentContainer, ScheduleFragment.newInstance())
                        }
                    }
                    binding.scheduleChooseGroupChipGroup.addView(chip)
                }
            }
            if (viewCount==0){
                binding.tvSelectGroupAllGroups.visibility = INVISIBLE
                val studyGroupNotFound = layoutInflater.inflate(R.layout.item_no_such_group_found, binding.scheduleChooseGroupChipGroup, false)
                studyGroupNotFound.apply {
                }
                binding.scheduleChooseGroupChipGroup.addView(studyGroupNotFound)
            }
        }

        //Для безопасности, чтобы выбрать можно было только лишь один чип (дейл)
        binding.scheduleChooseGroupChipGroup.isSingleSelection = true

        //Создание чипов
        for (i in 0..groupList.size-1){
            val chip = Chip(context)
            chip.apply {
                this.id = i
                this.text = groupList[i]
                /*this.style="@style/Widget.Material3.Chip.Suggestion.Elevated"*/
                this.setOnClickListener{
                    dataModel.studyGroup.value = groupList[i]
                    dataModel.mainToolBarTitle.value = getString(R.string.main_toolbar_description_schedule)
                    changeContentFragmentByChooseGroupFragment(R.id.contentContainer, ScheduleFragment.newInstance())
                }
            }
            binding.scheduleChooseGroupChipGroup.addView(chip)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ScheduleChooseGroupFragment()
    }

    private fun changeContentFragmentByChooseGroupFragment(idContainer: Int, newFragment:Fragment){
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(idContainer, newFragment)
            .commit()
    }
}