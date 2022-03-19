package com.application.kgtuapp.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel: ViewModel() {
    val mainToolBarTitle: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val studyGroup : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}