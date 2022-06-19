package com.application.kgtuapp.screens.notifications

data class Notification(
    val notificationId: Int,
    val isViewed: Boolean,
    val imageId: Int,
    val titleText: String,
    val notificationText: String,
    val authorName: String,
    val dateTime: String,
    val links: List<LinksInNotification>
)
