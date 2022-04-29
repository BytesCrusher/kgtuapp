package com.application.kgtuapp.DataClasses

data class RemoteInstitute(
    val id: Int,
    val instituteName: String,
    val groups: List<RemoteStudyGroup>
) {
}