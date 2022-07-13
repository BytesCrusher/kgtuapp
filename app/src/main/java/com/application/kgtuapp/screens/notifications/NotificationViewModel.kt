package com.application.kgtuapp.screens.notifications

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.kgtuapp.screens.scheduleChooseGroup.ScheduleChooseGroupRepository
import com.application.kgtuapp.screens.scheduleChooseGroup.data.RemoteInstitute

class NotificationViewModel : ViewModel() {

    val notificationDataLiveData = MutableLiveData<Notification>()

   /* fun getNotificationData(): Notification {
         val data = notificationDataLiveData.value.let {

                 Notification(
                     notificationId = it!!.notificationId,
                     isViewed = it.isViewed,
                     imageId = it.imageId,
                     titleText = it.titleText,
                     notificationText = it.notificationText,
                     authorName = it.authorName,
                     dateTime = it.dateTime,
                     links = it.links
                 )
        }

        return data
    }*/

}