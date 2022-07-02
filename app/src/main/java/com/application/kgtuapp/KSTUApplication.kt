package com.application.kgtuapp

import android.app.Application
import com.application.kgtuapp.screens.notifications.NotificationsService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KSTUApplication : Application(){
    val notificationsService = NotificationsService()

}