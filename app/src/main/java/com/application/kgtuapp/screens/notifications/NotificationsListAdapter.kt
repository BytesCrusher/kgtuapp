package com.application.kgtuapp.screens.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.ItemNotificationInNotificationListBinding

class NotificationsListAdapter(
    private val actionListener: NotificationsActionListener
) : RecyclerView.Adapter<NotificationsListAdapter.NotificationsHolder>(), View.OnClickListener {

    var notificationList: List<Notification> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onClick(view: View) {
        println("itemView.tag в методе onClick = ${view.tag}")
        val notification = view.tag as Notification
        actionListener.onNotificationDetails(notification)

        println("hello from onClick method")
        //смотрим куда же нажал пользователь
        when (view.id) {
            R.id.notification_layout -> {
                actionListener.onNotificationDetails(notification)
            }
            else -> {
                actionListener.onNotificationDetails(notification)
            }
        }
        //notifyDataSetChanged()
    }

    class NotificationsHolder(val binding: ItemNotificationInNotificationListBinding) : RecyclerView.ViewHolder(binding.root) {
        /*val binding = ItemNotificationBinding.bind(item)

        fun bind(notification: Notification) = with(binding) {
            itemView.tag = notification
            println("itemView.tag = ${itemView.tag}")
            imNotificationIcon.setImageResource(notification.imageId)
            tvNotificationTitle.text = notification.titleText
            tvNotificationBody.text = notification.notificationText
            tvNotificationAuthor.text = notification.authorName
            tvNotificationDateTime.text = notification.dateTime

            if (notification.isViewed) {
                imNotificationIsViewed.visibility = GONE
            }
        }*/


    }

    //Метод работает когда нужно создать новый элемент списка
    // берет разметку, надувает ее и создает класс NotificationsHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsHolder {
        /*val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)

        *//*val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNotificationBinding.inflate(inflater, parent, false)

        //инициализируем слушатели нажатий root - это cardNotification
        binding.cardNotification.setOnClickListener { this }*//*
        //cardNotification.setOnClickListener {  }
        val notificationLayout = view.findViewById<LinearLayout>(R.id.notification_layout)
        notificationLayout.setOnClickListener(this)

        //println("view.tag from onCreateViewHolder " + view.tag)

        return NotificationsHolder(view)*/

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNotificationInNotificationListBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

        return NotificationsHolder(binding)
    }

    //откуда берем данные
    override fun onBindViewHolder(holder: NotificationsHolder, position: Int) {
        val notification = notificationList[position]
        println("notification from onBindViewHolder => " + notification)

        //добавляем теги каждому уведомлению
        holder.itemView.tag = notification

        with(holder.binding) {
            imNotificationIcon.setImageResource(notification.imageId)
            tvNotificationTitle.text = notification.titleText
            tvNotificationBody.text = notification.notificationText
            tvNotificationAuthor.text = notification.authorName
            tvNotificationDateTime.text = notification.dateTime

            if (notification.isViewed) {
                imNotificationIsViewed.visibility = GONE
            } else {
                imNotificationIsViewed.visibility = VISIBLE
            }
        }
        //holder.bind(notification)

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

