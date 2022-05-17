package com.application.kgtuapp.Schedule

import com.google.gson.annotations.SerializedName

data class RetrofitCreateElementRequest(
    @SerializedName("group") val group: String
)