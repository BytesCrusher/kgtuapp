package com.application.kgtuapp.screens.notifications

import com.application.kgtuapp.BuildConfig
import com.application.kgtuapp.R
import java.util.*

typealias NotificationsListener = (notificationList: List<Notification>) -> Unit

//это будет класс синглтон
class NotificationsService {

    private var notificationList = mutableListOf<Notification>()

    //сюда добавляем слушатели, Которые будут прослушивать изменения в классе NotificationsService
    private val listeners = mutableListOf<NotificationsListener>()

    init {
        val imageIdList = mutableListOf(
            R.drawable.ic_notifications_calendar,
            R.drawable.ic_notifications_critical
        )

        notificationList = (1..100).map {
            Notification(
                notificationId = it,
                isViewed = false,
                imageId = imageIdList.random(),
                titleText = "Добро пожаловать в КГТУ Апп. ($it)",
                notificationText = "Вы используете приложение:\n" +
                        "Вариант сборки = ${BuildConfig.BUILD_TYPE}\n" +
                        "Версия приложения = ${BuildConfig.VERSION_NAME}\n" +
                        "Код версии приложения = ${BuildConfig.VERSION_CODE}",
                authorName = "ИЦТ КГТУ",
                dateTime = "7 мая, 15:17",
                links = listOf(
                    LinksInNotification(
                        linkTitle = "Новости от команды разработчиков доступны в телеграм-канале:",
                        link = "https://t.me/kstuapp"
                    )
                )
            )
        }.toMutableList()
    }

    //получение уведомлений
    fun getNotifications(): List<Notification>{
        return notificationList
    }

    //удаление уведомления
    fun deleteNotification(notification: Notification){
        val indexToDelete = notificationList.indexOfFirst { it.notificationId == notification.notificationId }
        if (indexToDelete != -1){
            notificationList.removeAt((indexToDelete))
            notifyChanges()
        }
    }

    fun makeNotificationViewed(notification: Notification){
        notificationList[
                notificationList.indexOfFirst {
                    it.notificationId == notification.notificationId
                }].isViewed = true
        notifyChanges()
    }

    //перемещение уведомления вверх/вниз
    //какое уведомление, вниз или вверх (- вверх, + вниз)
    fun moveNotification(notification: Notification, moveBy: Int){
        val oldIndex = notificationList.indexOfFirst { it.notificationId == notification.notificationId }
        if (oldIndex == -1) return
        val newIndex = oldIndex + moveBy
        if (newIndex<0 || newIndex>= notificationList.size) return
        Collections.swap(notificationList, oldIndex, newIndex)
        notifyChanges()
    }

    //добавление лиснера
    fun addListener(listener: NotificationsListener){
        listeners.add(listener)
        listener.invoke(notificationList)
    }

    //удаление лиснера
    fun removeListener(listener: NotificationsListener){
        listeners.remove(listener)
    }

    //
    private fun notifyChanges(){
        listeners.forEach { it.invoke(notificationList) }
    }
}