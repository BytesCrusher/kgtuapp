package com.application.kgtuapp.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.application.kgtuapp.R
import com.application.kgtuapp.ViewModels.DataModel
import com.application.kgtuapp.databinding.FragmentScheduleChooseGroupBinding
import com.google.android.material.chip.Chip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class ScheduleChooseGroupFragment : Fragment(R.layout.fragment_schedule_choose_group) {
    private lateinit var binding: FragmentScheduleChooseGroupBinding
    private val dataModel : DataModel by activityViewModels()

    //доступ к файлу Preferences
    /*val sharedPrefs =
        requireContext().getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)*/
    private val sharedPrefs by lazy{
        requireContext().getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)}

    val groupList = listOf<String>("18-вт", "20-ап", "20-мс", "20-вт", "20-кс", "19-иэ", "19-ап", "19-вт", "21-ПБм")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleChooseGroupBinding.inflate(layoutInflater, container, false)

        binding.ibToolbarGoBack.setOnClickListener {
            changeContentFragmentByChooseGroupFragment(
                R.id.l_mainActivityFragment,
                ScheduleFragment.newInstance()
            )
        }

        //Поисковик по группам
        binding.etGroupSearch.addTextChangedListener {
            binding.scheduleChooseGroupChipGroup.removeAllViews()
            binding.tvSelectGroupAllGroups.visibility = VISIBLE
            val currentEntryString = binding.etGroupSearch.text.toString().toCharArray()

            if (binding.etGroupSearch.text.toString()==""){
                binding.tvSelectGroupAllGroups.text = getString(R.string.tv_schedule_choose_group_all_groups)
            } else {
                binding.tvSelectGroupAllGroups.text = getString(R.string.tv_schedule_choose_group_groups_found)
            }

            var fullString = ""

            //Приводим строку к нижнему регистру
            for (i in currentEntryString.indices) {
                fullString += if (currentEntryString[i].isUpperCase()) {
                    currentEntryString[i].lowercaseChar()
                } else {
                    currentEntryString[i]
                }
            }

            var viewCount = 0
            /*val viewCount = makeChips()*/
            for (i in 0..groupList.size-1){
                if (fullString in groupList[i]){
                    viewCount+=1
                    val chip = Chip(context)//Chip(context)//layoutInflater.inflate(R.layout.item_chip_choose_group, binding.scheduleChooseGroupChipGroup, false)
                    chip.apply {
                        //val chip_element = this.findViewById<Chip>(R.id.chip_studyGroup)
                        this.id = i
                        this.text = groupList[i].uppercase(Locale.getDefault())
                        /*this.style="@style/Widget.Material3.Chip.Suggestion.Elevated"*/
                        this.setOnClickListener{
                            it.setBackgroundColor(resources.getColor(R.color.md_theme_light_secondaryContainer))
                            saveStudyGroupToPreferences(i)
                            dataModel.mainToolBarTitle.value = getString(R.string.main_toolbar_description_schedule)
                            changeContentFragmentByChooseGroupFragment(R.id.l_mainActivityFragment, ScheduleFragment.newInstance())
                        }
                    }
                    binding.scheduleChooseGroupChipGroup.addView(chip)
                }
            }
            if (viewCount==0){
                binding.tvSelectGroupAllGroups.visibility = INVISIBLE
                val studyGroupNotFound =
                    layoutInflater.inflate(R.layout.item_no_such_group_found,
                        binding.scheduleChooseGroupChipGroup,
                        true)
                /*studyGroupNotFound.apply {
                }
                binding.scheduleChooseGroupChipGroup.addView(studyGroupNotFound)*/
            }
        }

        //Для безопасности, чтобы выбрать можно было только лишь один чип (дейл)
        binding.scheduleChooseGroupChipGroup.isSingleSelection = true

        //Создание чипов
        makeChips()

        return binding.root
    }

    private fun saveStudyGroupToPreferences(index: Int){
        dataModel.studyGroup.value = groupList[index].uppercase(Locale.getDefault())
        /*val sharedPrefs =
            requireContext().getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)*/
        lifecycleScope.launch(Dispatchers.IO) {
            sharedPrefs.edit()
                .putString(USER_STUDY_GROUP, dataModel.studyGroup.value.toString())
                .apply()
        }
    }

    //в этом фрагменте не используется
    private fun checkStudyGroupFromPreferences(){
        lifecycleScope.launch(Dispatchers.IO) {
            sharedPrefs.getString(USER_STUDY_GROUP, null)
        }
    }

    private fun makeChips(): Int{
        var viewCount = 0
        for (i in 0..groupList.size-1){
            viewCount+=1
            //val chipStyle = R.style.Widget_Material3_Chip_Suggestion_Elevated
            val chip = Chip(context)//Chip(context)//layoutInflater.inflate(R.layout.item_chip_choose_group, binding.scheduleChooseGroupChipGroup, false)
            chip.apply {
                //val chip_element = this.findViewById<Chip>(R.id.chip_studyGroup)
                this.id = i
                this.text = groupList[i].uppercase(Locale.getDefault())
                //chip_element.text = groupList[i].uppercase(Locale.getDefault())
                /*this.style="@style/Widget.Material3.Chip.Suggestion.Elevated"*/
                this.setOnClickListener{
                    it.setBackgroundColor(resources.getColor(R.color.md_theme_light_secondaryContainer))
                    saveStudyGroupToPreferences(i)
                    dataModel.mainToolBarTitle.value = getString(R.string.main_toolbar_description_schedule)
                    changeContentFragmentByChooseGroupFragment(R.id.l_mainActivityFragment, ScheduleFragment.newInstance())
                }
            }
            binding.scheduleChooseGroupChipGroup.addView(chip)
        }
        return viewCount
    }

    companion object {
        @JvmStatic
        fun newInstance() = ScheduleChooseGroupFragment()
        private const val SHARED_PREFS_NAME = "user_data_shared_prefs"
        private const val USER_STUDY_GROUP = "user study group"
    }

    private fun changeContentFragmentByChooseGroupFragment(idContainer: Int, newFragment:Fragment){
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(idContainer, newFragment)
            .commit()
    }
}