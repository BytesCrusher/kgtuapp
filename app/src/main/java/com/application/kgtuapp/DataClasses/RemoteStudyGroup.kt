package com.application.kgtuapp.DataClasses

data class RemoteStudyGroup(
    val id: Int,
    val name: String,
    val institute: Int,
    val subGroups: List<RemoteStudySubGroup>
) {
}