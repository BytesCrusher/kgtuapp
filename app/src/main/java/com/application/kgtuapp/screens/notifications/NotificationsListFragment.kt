package com.application.kgtuapp.screens.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.kgtuapp.BuildConfig
import com.application.kgtuapp.KSTUApplication
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.FragmentNotificationsListBinding
import com.application.kgtuapp.screens.schedule.ScheduleFragment

class NotificationsListFragment : Fragment() {
    private lateinit var binding: FragmentNotificationsListBinding

    //адаптер для rcView
    private var adapter = NotificationsAdapter()

    //геттер для получения доступа к модели сервиса уведомлений (NotificationsService)
    private val notificationsService: NotificationsService
        get() = (requireContext().applicationContext as KSTUApplication).notificationsService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsListBinding.inflate(layoutInflater, container, false)


        binding.apply {
            ibToolbarGoBack.setOnClickListener {
                changeContentFragmentByNotificationListFragment(
                    R.id.l_mainActivityFragment,
                    ScheduleFragment.newInstance()
                )
            }

            rcViewNotifications.layoutManager =
                LinearLayoutManager(this@NotificationsListFragment.context)
            rcViewNotifications.adapter = adapter

            notificationsService.addListener(notificationsListener)

        }

        //просто проверка что там лежит
        //notificationsService.getNotifications().forEach { println(it.notificationId) }

        return binding.root
    }

    private fun changeContentFragmentByNotificationListFragment(
        idContainer: Int,
        newFragment: Fragment
    ) {
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(idContainer, newFragment)
            .commit()
    }

    //слушатель изменений списка уведомлений
    private val notificationsListener: NotificationsListener = {
        adapter.notificationList = it
    }

    //для защиты от утечки памяти удаляем слушатель
    override fun onDestroy() {
        super.onDestroy()
        notificationsService.removeListener(notificationsListener)
    }

    /*private fun init() {
        val imageIdList = listOf(
            R.drawable.ic_notifications_calendar,
            R.drawable.ic_notifications_critical
        )

        binding.apply {
            ibToolbarGoBack.setOnClickListener {
                changeContentFragmentByNotificationListFragment(
                    R.id.l_mainActivityFragment,
                    ScheduleFragment.newInstance()
                )
            }

            rcViewNotifications.layoutManager =
                LinearLayoutManager(this@NotificationsListFragment.context)
            rcViewNotifications.adapter = adapter



            mainToolBar.setOnClickListener {
                val notification = Notification(
                    notificationId = 0,
                    isViewed = false,
                    imageId = imageIdList.random(),
                    titleText = "Добро пожаловать в КГТУ Апп.",
                    notificationText = "Текст уведомления, который может быть очень-очень большим\n" +
                            "Вы используете приложение:\n" +
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
                //adapter.addNotification(notification)
                //return@setOnLongClickListener true
            }


        }
    }*/

    companion object {
        @JvmStatic
        fun newInstance() = NotificationsListFragment()
    }
}