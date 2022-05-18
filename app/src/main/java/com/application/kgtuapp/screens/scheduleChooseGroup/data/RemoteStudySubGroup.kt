package com.application.kgtuapp.screens.scheduleChooseGroup.data

data class RemoteStudySubGroup(
    val id: Int,
    val parentStudyGroupId: Int,
    val numberInParentGroup: Int,
    val subGroupName: String
) {
}