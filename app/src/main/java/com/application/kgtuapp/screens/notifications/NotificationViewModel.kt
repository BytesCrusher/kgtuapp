package com.application.kgtuapp.screens.notifications

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.kgtuapp.screens.scheduleChooseGroup.ScheduleChooseGroupRepository
import com.application.kgtuapp.screens.scheduleChooseGroup.data.RemoteInstitute

class NotificationViewModel: ViewModel() {

        val notificationDataLiveData = MutableLiveData<Notification>()

}