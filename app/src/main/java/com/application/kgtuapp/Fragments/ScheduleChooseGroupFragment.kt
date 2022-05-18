package com.application.kgtuapp.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.application.kgtuapp.R
import com.application.kgtuapp.ViewModels.DataModel
import com.application.kgtuapp.ViewModels.InstitutesDataListModel
import com.application.kgtuapp.databinding.FragmentScheduleChooseGroupBinding
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScheduleChooseGroupFragment : Fragment(R.layout.fragment_schedule_choose_group) {
    private lateinit var binding: FragmentScheduleChooseGroupBinding
    private val dataModel: DataModel by activityViewModels()

    //доступ к файлу Preferences
    /*val sharedPrefs =
        requireContext().getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)*/
    private val sharedPrefs by lazy {
        requireContext().getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }

    //val groupList = listOf<String>("18-вт", "20-ап", "20-мс", "20-вт", "20-кс", "19-иэ", "19-ап", "19-вт", "21-ПБм")

    private val viewModel: InstitutesDataListModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScheduleChooseGroupBinding.inflate(layoutInflater, container, false)

        binding.ibToolbarGoBack.setOnClickListener {
            changeContentFragmentByChooseGroupFragment(
                R.id.l_mainActivityFragment,
                ScheduleFragment.newInstance()
            )
        }

        //получение данных с API
        viewModel.search()
        viewModel.institutesDataListLiveData.observe(viewLifecycleOwner) {
            makeChips()
            //здесь какой кринж написан
            /*it.forEach {
                makeChips()
                //binding.tvApkVersion.text = "${binding.tvApkVersion.text} + ${it.id} + ${it.instituteName} ||"
            }*/
        }

        //Поисковик по группам
        binding.etGroupSearch.addTextChangedListener {
            groupSearch()

        }

        //Для безопасности, чтобы выбрать можно было только лишь один чип (дейл)
        binding.scheduleChooseGroupChipGroup.isSingleSelection = true

        //Создание чипов
        makeChips()

        return binding.root
    }

    private fun saveStudyGroupToPreferences(studyGroupName: String, studyGroupId: Int) {
        dataModel.studyGroup.value = studyGroupName
        lifecycleScope.launch(Dispatchers.IO) {
            sharedPrefs.edit()
                .putString(USER_STUDY_GROUP, studyGroupName)
                .apply()
            sharedPrefs.edit()
                .putInt(USER_STUDY_GROUP_ID, studyGroupId)
                .apply()
        }
    }

    //в этом фрагменте не используется
    private fun checkStudyGroupFromPreferences() {
        lifecycleScope.launch(Dispatchers.IO) {
            sharedPrefs.getString(USER_STUDY_GROUP, null)
        }
    }

    private fun stringToLowerCase(initialString: String): String {
        var lowerString = ""

        //Приводим строку к нижнему регистру
        initialString.forEach {
            lowerString += if (it.isUpperCase()) {
                it.lowercaseChar()
            } else {
                it
            }
        }
        return lowerString
    }

    //поиск группы
    private fun groupSearch() {
        binding.scheduleChooseGroupChipGroup.removeAllViews()
        binding.tvSelectGroupAllGroups.visibility = VISIBLE
        val currentEntryString = binding.etGroupSearch.text.toString()

        if (binding.etGroupSearch.text.toString() == "") {
            binding.tvSelectGroupAllGroups.text =
                getString(R.string.tv_schedule_choose_group_all_groups)
        } else {
            binding.tvSelectGroupAllGroups.text =
                getString(R.string.tv_schedule_choose_group_groups_found)
        }

        val userInputForGroupSearch = stringToLowerCase(currentEntryString)//""

        var viewCount = 0
        viewModel.institutesDataListLiveData.value?.forEach {
            //заголовок института
            val newDay = layoutInflater.inflate(
                R.layout.item_institute_name,
                binding.scheduleChooseGroupChipGroup,
                false
            )
            newDay.apply {
                val instituteTitle = this.findViewById<TextView>(R.id.tv_instituteName)
                instituteTitle.text = it.instituteName
                if (viewCount > 0) {
                    instituteTitle.marginTop.plus(20)
                }
            }
            binding.scheduleChooseGroupChipGroup.addView(newDay)

            //проверка самих групп
            it.groups.forEach {
                val studyGroupName = it.name
                if (it.subGroups.isEmpty()) {
                    if (userInputForGroupSearch in stringToLowerCase(studyGroupName)) {
                        viewCount += 1
                        val chip = Chip(context)
                        chip.apply {
                            val chipId = it.id
                            this.id = chipId
                            this.text = studyGroupName
                            this.setOnClickListener {
                                //it.setBackgroundColor(resources.getColor(R.color.md_theme_light_secondaryContainer))
                                saveStudyGroupToPreferences(studyGroupName, chipId)
                                dataModel.mainToolBarTitle.value =
                                    getString(R.string.main_toolbar_description_schedule)
                                changeContentFragmentByChooseGroupFragment(
                                    R.id.l_mainActivityFragment,
                                    ScheduleFragment.newInstance()
                                )
                            }
                        }
                        binding.scheduleChooseGroupChipGroup.addView(chip)
                    }
                } else {
                    it.subGroups.forEach {
                        val studySubGroupName = it.subGroupName
                        if (userInputForGroupSearch in stringToLowerCase(studySubGroupName)) {
                            viewCount += 1
                            val chip = Chip(context)
                            chip.apply {
                                val chipId = it.id
                                this.id = chipId
                                this.text = studySubGroupName
                                this.setOnClickListener {
                                    //it.setBackgroundColor(resources.getColor(R.color.md_theme_light_secondaryContainer))
                                    saveStudyGroupToPreferences(studySubGroupName, chipId)
                                    dataModel.mainToolBarTitle.value =
                                        getString(R.string.main_toolbar_description_schedule)
                                    changeContentFragmentByChooseGroupFragment(
                                        R.id.l_mainActivityFragment,
                                        ScheduleFragment.newInstance()
                                    )
                                }
                            }
                            binding.scheduleChooseGroupChipGroup.addView(chip)
                        }
                    }
                }
            }
        }

        if (viewCount == 0) {
            binding.scheduleChooseGroupChipGroup.removeAllViews()
            binding.tvSelectGroupAllGroups.visibility = INVISIBLE
            val studyGroupNotFound =
                layoutInflater.inflate(
                    R.layout.item_no_such_group_found,
                    binding.scheduleChooseGroupChipGroup,
                    true
                )
        }
    }

    private fun makeChips(): Int {
        binding.scheduleChooseGroupChipGroup.removeAllViews()
        var viewCount = 0

        viewModel.institutesDataListLiveData.value?.forEach {
            //заголовок института
            val newDay = layoutInflater.inflate(
                R.layout.item_institute_name,
                binding.scheduleChooseGroupChipGroup,
                false
            )
            newDay.apply {
                val instituteTitle = this.findViewById<TextView>(R.id.tv_instituteName)
                instituteTitle.text = it.instituteName
                if (viewCount > 0) {
                    instituteTitle.marginTop.plus(20)
                }
            }
            binding.scheduleChooseGroupChipGroup.addView(newDay)

            it.groups.forEach {
                if (it.subGroups.isEmpty()) {
                    viewCount += 1
                    val chip = Chip(context)
                    chip.apply {
                        val studyGroupName = it.name
                        val chipId = it.id
                        this.id = chipId
                        this.text = studyGroupName//groupList[i].uppercase(Locale.getDefault())
                        this.setOnClickListener {
                            //it.setBackgroundColor(resources.getColor(R.color.md_theme_light_secondaryContainer))
                            saveStudyGroupToPreferences(studyGroupName, chipId)
                            dataModel.mainToolBarTitle.value =
                                getString(R.string.main_toolbar_description_schedule)
                            changeContentFragmentByChooseGroupFragment(
                                R.id.l_mainActivityFragment,
                                ScheduleFragment.newInstance()
                            )
                        }
                    }
                    binding.scheduleChooseGroupChipGroup.addView(chip)
                } else {
                    it.subGroups.forEach {
                        viewCount += 1
                        val chip = Chip(context)
                        chip.apply {
                            val studySubGroupName = it.subGroupName
                            val shipId = it.id
                            this.id = shipId
                            this.text = studySubGroupName
                            this.setOnClickListener {
                                //it.setBackgroundColor(resources.getColor(R.color.md_theme_light_secondaryContainer))
                                saveStudyGroupToPreferences(studySubGroupName, shipId)
                                dataModel.mainToolBarTitle.value =
                                    getString(R.string.main_toolbar_description_schedule)
                                changeContentFragmentByChooseGroupFragment(
                                    R.id.l_mainActivityFragment,
                                    ScheduleFragment.newInstance()
                                )
                            }
                        }
                        binding.scheduleChooseGroupChipGroup.addView(chip)
                    }
                }
            }
        }
        return viewCount
    }

    companion object {
        @JvmStatic
        fun newInstance() = ScheduleChooseGroupFragment()
        private const val SHARED_PREFS_NAME = "user_data_shared_prefs"
        private const val USER_STUDY_GROUP = "user study group"
        private const val USER_STUDY_GROUP_ID = "user study group id"
    }

    private fun changeContentFragmentByChooseGroupFragment(
        idContainer: Int,
        newFragment: Fragment
    ) {
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(idContainer, newFragment)
            .commit()
    }
}