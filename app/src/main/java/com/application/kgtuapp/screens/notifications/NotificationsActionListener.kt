package com.application.kgtuapp.screens.notifications

interface NotificationsActionListener {
    fun onNotificationMove(notification: Notification, moveBy: Int)

    fun onNotificationDelete(notification: Notification)

    fun onNotificationDetails(notification: Notification)

}