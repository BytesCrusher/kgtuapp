package com.application.kgtuapp.screens.notifications

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.ItemNotificationBinding

class NotificationsAdapter:RecyclerView.Adapter<NotificationsAdapter.NotificationsHolder>() {

    var notificationList: List<Notification> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class NotificationsHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = ItemNotificationBinding.bind(item)

        fun bind(notification: Notification) = with(binding){
            imNotificationIcon.setImageResource(notification.imageId)
            tvNotificationTitle.text = notification.titleText
            tvNotificationBody.text = notification.notificationText
            tvNotificationAuthor.text = notification.authorName
            tvNotificationDateTime.text = notification.dateTime

            if (notification.isViewed) {
                imNotificationIsViewed.visibility = GONE
            }
        }
    }

    //Метод работает когда нужно создать новый элемент списка
    // берет разметку, надувает ее и создает класс NotificationsHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return NotificationsHolder(view)
    }

    //откуда берем данные
    override fun onBindViewHolder(holder: NotificationsHolder, position: Int) {
        holder.bind(notificationList[position])
    }

    //количество создаваемых элементов
    override fun getItemCount(): Int {
        return notificationList.size
    }

    //функция для добавления нового элемента
    /*fun addNotification(notification: Notification):Unit{
        notificationList.add(notification)
        notifyDataSetChanged()
    }*/
}

