package com.application.kgtuapp.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

open class DataModel: ViewModel() {
    val isUserAutorized : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val mainToolBarTitle: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val studyGroup : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}