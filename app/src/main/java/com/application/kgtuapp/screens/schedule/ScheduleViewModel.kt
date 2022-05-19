package com.application.kgtuapp.screens.schedule

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(private val scheduleGoApi: ScheduleGoApi) : ViewModel() {

    fun sendPostRequest() {
        viewModelScope.launch {
            try {
                val responseString = scheduleGoApi.sendElementRequest(
                    RetrofitCreatePostScheduleRequest(
                        group = "19-АП(эс)"
                    )
                )
                Log.d("Server", "response string = ${responseString}")

            } catch (e: Exception) {
                Log.e("TAG", "Exception during request -> ${e.localizedMessage}")
            }
        }
    }
}