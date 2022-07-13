package com.application.kgtuapp.screens.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.kgtuapp.R
import com.application.kgtuapp.screens.notifications.oneNotification.BaseCell
import com.application.kgtuapp.screens.notifications.oneNotification.NotificationBody
import com.application.kgtuapp.screens.notifications.oneNotification.NotificationHeader

/*class NotificationAdapter(
    val notificationData: MutableLiveData<Notification>
) : RecyclerView.Adapter<NotificationAdapter.NotificationHeaderHolder>() {*/
class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.BaseViewHolder<*>>() {


    var testData : MutableList<BaseCell> = ArrayList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    companion object {
        private const val TYPE_HEADER =  1
        private const val TYPE_LINKS =  2
    }

    fun setData(items: MutableList<BaseCell>) {
        this.testData.clear()
        this.testData = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType)
        {
            TYPE_HEADER -> HeaderViewHolder(layoutInflater.inflate(R.layout.item_one_notification_header, parent, false))
            TYPE_LINKS -> LinksViewHolder(layoutInflater.inflate(R.layout.item_one_notification_links, parent, false))
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = testData[position]
        when(holder) {
            is HeaderViewHolder -> holder.bind(element as NotificationHeader)
            is LinksViewHolder -> holder.bind(element as NotificationBody)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(testData[position]) {
            is NotificationBody -> TYPE_LINKS
            is NotificationHeader -> TYPE_HEADER
            else -> throw IllegalArgumentException("Invalid type of item $position")
        }
    }

    override fun getItemCount(): Int {
        return testData.size
    }

    abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(item: T)
    }

    class LinksViewHolder(view: View) : BaseViewHolder< NotificationBody>(view) {
        private val testTextView = view.findViewById<TextView>(R.id.tv_linkTitle)

        override fun bind(item: NotificationBody) {
            testTextView.text = item.linkTitle//notificationData.value?.titleText ?: "Нет данных"
        }
    }

    class HeaderViewHolder(view: View) : BaseViewHolder<NotificationHeader>(view) {
        private val testTextView = view.findViewById<TextView>(R.id.tv_notification_title)

        override fun bind(item: NotificationHeader) {
            testTextView.text = item.titleText//notificationData.value?.titleText ?: "Нет данных"
        }
    }
}
