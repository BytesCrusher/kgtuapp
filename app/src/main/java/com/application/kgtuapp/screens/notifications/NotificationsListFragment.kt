package com.application.kgtuapp.screens.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.FragmentNotificationsListBinding
import com.application.kgtuapp.screens.schedule.ScheduleFragment

class NotificationsListFragment : Fragment() {
    private lateinit var binding: FragmentNotificationsListBinding

    //адаптер для rcView
    private var adapter = NotificationsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsListBinding.inflate(layoutInflater, container, false)



        init()

        return binding.root
    }

    private fun changeContentFragmentByNotificationListFragment(idContainer: Int, newFragment:Fragment){
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(idContainer, newFragment)
            .commit()
    }

    private fun init(){
        val imageIdList = listOf(
            R.drawable.ic_notifications_calendar,
            R.drawable.ic_notifications_critical)

        binding.apply {
            ibToolbarGoBack.setOnClickListener {
                changeContentFragmentByNotificationListFragment(
                    R.id.l_mainActivityFragment,
                    ScheduleFragment.newInstance()
                )
            }

            rcViewNotifications.layoutManager = LinearLayoutManager(this@NotificationsListFragment.context)
            rcViewNotifications.adapter = adapter

            mainToolBar.setOnClickListener {
                val notification = Notification(
                    notificationId = 0,
                    isViewed = false,
                    imageId = imageIdList.random(),
                    titleText = "Добро пожаловать в КГТУ Апп.",
                    notificationText = "Вы используете приложение:\n" +
                            "Вариант сборки = debug\n" +
                            "Версия приложения = 0.1\n" +
                            "Код версии приложения = 1",
                    authorName = "ИЦТ КГТУ",
                    dateTime = "7 мая, 15:17",
                    links = listOf(LinksInNotification(
                        linkTitle = "Новости от команды разработчиков доступны в телеграм-канале:",
                        link = "https://t.me/kstuapp"
                    ))
                )
                adapter.addNotification(notification)
                //return@setOnLongClickListener true
            }


        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotificationsListFragment()
    }
}