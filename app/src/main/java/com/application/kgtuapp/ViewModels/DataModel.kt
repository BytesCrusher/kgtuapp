package com.application.kgtuapp.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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