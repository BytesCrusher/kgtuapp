package com.application.kgtuapp.screens.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.FragmentNotificationBinding
import com.application.kgtuapp.screens.notifications.oneNotification.BaseCell
import com.application.kgtuapp.screens.notifications.oneNotification.NotificationBody
import com.application.kgtuapp.screens.notifications.oneNotification.NotificationHeader

class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding

    private val viewModel: NotificationViewModel by viewModels()

    private var adapter = NotificationAdapter()//viewModel.notificationDataLiveData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(layoutInflater, container, false)

        binding.ibToolbarGoBack.setOnClickListener {
            changeContentFragmentByNotificationFragment(
                R.id.l_mainActivityFragment,
                NotificationsListFragment.newInstance()
            )
        }

        binding.rvOneNotification.adapter = adapter
        binding.rvOneNotification.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        //println("viewModel.getNotificationData() = " + viewModel.getNotificationData())

        val items: MutableList<BaseCell> = ArrayList()
        //перекладываем данные из класса во вью модели в список для вывода на экран
        /*with(viewModel.notificationDataLiveData.value) {

            items.add(
                NotificationHeader(
                    titleText = this?.titleText ?: "Ошибка! Данных нет",
                    notificationText = this?.notificationText ?: "Ошибка! Данных нет",
                    authorName = this?.authorName ?: "Ошибка! Данных нет",
                    dateTime = this?.dateTime ?: "Ошибка! Данных нет"
                )
            )

            this?.links?.forEach {
                items.add(
                    NotificationBody(
                        linkTitle = it.linkTitle,
                        link = it.link
                    )
                )
            }
        }*/

        //println("items = \n" + items)


        /*items.add(
            NotificationHeader(
                titleText = "Заголовок",
                notificationText = "Текст уведомления",
                authorName = "Автор уведомления",
                dateTime = "7 мая, 14:42"
            )
        )

        items.add(
            NotificationBody(
                linkTitle = "Заголовок ссылки",
                link = "https://t.me/kstuapp"
            )
        )

        adapter.setData(items)*/


        /*viewModel.search()
        viewModel.institutesDataListLiveData.observe(viewLifecycleOwner){
            it.forEach {
                binding.tvApkVersion.text = "${binding.tvApkVersion.text} + ${it.id} + ${it.instituteName} ||"
            }
        }*/
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotificationFragment()
    }

    private fun changeContentFragmentByNotificationFragment(
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
    /*private val notificationsListener: NotificationsListener = {
        adapter.testData = it
    }*/

    //для защиты от утечки памяти удаляем слушатель
    /*override fun onDestroy() {
        super.onDestroy()
        notificationsService.removeListener(notificationsListener)
    }*/
}