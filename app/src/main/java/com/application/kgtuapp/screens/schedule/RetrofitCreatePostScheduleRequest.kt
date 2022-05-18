package com.application.kgtuapp.screens.schedule

import com.google.gson.annotations.SerializedName

data class RetrofitCreatePostScheduleRequest(
    @SerializedName("group") val group: String
)