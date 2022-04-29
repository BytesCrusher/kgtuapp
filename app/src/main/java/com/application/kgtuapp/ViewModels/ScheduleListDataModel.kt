package com.application.kgtuapp.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.kgtuapp.DataClasses.RemoteSchedule
import com.application.kgtuapp.Repositories.ScheduleRepository

class ScheduleListDataModel: ViewModel() {
    private val repository = ScheduleRepository()
    private val scheduleListLiveData = MutableLiveData<List<RemoteSchedule>>()

    fun search(query: String){
        repository.searchSchedule(
            query = query,
            onComplete = { users->
                scheduleListLiveData.postValue(users)
            },
            onError = { throwable ->
                scheduleListLiveData.postValue(emptyList())
            }
        )
    }
}