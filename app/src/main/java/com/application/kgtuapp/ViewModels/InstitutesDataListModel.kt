package com.application.kgtuapp.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.kgtuapp.DataClasses.RemoteInstitute
import com.application.kgtuapp.Repositories.UniversityStractureRepository
import dagger.hilt.android.lifecycle.HiltViewModel

class InstitutesDataListModel: ViewModel() {
    private val repository = UniversityStractureRepository()

    val institutesDataListLiveData = MutableLiveData<List<RemoteInstitute>>()

    fun search(){
        repository.searchGroupSchedule {
            institutes ->
            institutesDataListLiveData.postValue(institutes)
        }
    }
}