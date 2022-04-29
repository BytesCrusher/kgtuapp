package com.application.kgtuapp.DataClasses

data class RemoteSchedule(
    val id: Int,
    val scheduleList: List<RemoteClass>
) {
}