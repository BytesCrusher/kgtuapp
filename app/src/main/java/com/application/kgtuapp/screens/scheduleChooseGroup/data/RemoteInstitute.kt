package com.application.kgtuapp.screens.scheduleChooseGroup.data

data class RemoteInstitute(
    val id: Int,
    val instituteName: String,
    val groups: List<RemoteStudyGroup>
) {
}