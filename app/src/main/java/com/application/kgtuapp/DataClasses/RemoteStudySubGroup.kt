package com.application.kgtuapp.DataClasses

data class RemoteStudySubGroup(
    val id: Int,
    val parentStudyGroupId: Int,
    val numberInParentGroup: Int,
    val subGroupName: String
) {
}