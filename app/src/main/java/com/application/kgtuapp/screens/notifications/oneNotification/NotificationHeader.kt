package com.application.kgtuapp.screens.notifications.oneNotification

data class NotificationHeader (
    //val classNumber: String
    val titleText: String,
    val notificationText: String,
    val authorName: String,
    val dateTime: String
) : BaseCell