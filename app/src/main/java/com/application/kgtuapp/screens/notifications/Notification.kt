package com.application.kgtuapp.screens.notifications

data class Notification(
    val notificationId: Int,
    var isViewed: Boolean,
    val imageId: Int,
    var titleText: String,
    val notificationText: String,
    val authorName: String,
    val dateTime: String,
    val links: List<LinksInNotification>
)
