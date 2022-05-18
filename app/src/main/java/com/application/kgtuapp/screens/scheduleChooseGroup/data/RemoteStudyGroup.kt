package com.application.kgtuapp.screens.scheduleChooseGroup.data

data class RemoteStudyGroup(
    val id: Int,
    val name: String,
    val institute: Int,
    val subGroups: List<RemoteStudySubGroup>
) {
}