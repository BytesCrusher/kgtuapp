package com.application.kgtuapp.screens.scheduleChooseGroup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.kgtuapp.screens.scheduleChooseGroup.data.RemoteInstitute

class ScheduleChooseGroupViewModel: ViewModel() {
    private val repository = ScheduleChooseGroupRepository()

    val institutesDataListLiveData = MutableLiveData<List<RemoteInstitute>>()

    fun search(){
        repository.searchGroupSchedule {
            institutes ->
            institutesDataListLiveData.postValue(institutes)
        }
    }
}