package com.application.kgtuapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.kgtuapp.Classes.CertainClassInScheduleDay
import com.application.kgtuapp.R

class ScheduleRecyclerAdapter(
    private val scheduleDataMap: MutableMap<Int, MutableList<CertainClassInScheduleDay>>,

    //набор данных, которые надо показывать в разметке
    private val stringsList: List<String>,

    //айдишник элемента, внутри которого надо подставлять этот набор данных
    //R.id.tv_dayInfo
    private val textElementId: Int,

    //айдишник лаяута, внутри которого будет раздуваться эта разметка
    //R.layout.item_certain_day
    private val layoutItemId: Int,

    //количество выводимых элементов
    //указывать только когда надо выводить абсолютно одинаковые элементы,
    // для которых не нужно указывать набор данных stringsList
    private val itemCount: Int = 1
    ):
    RecyclerView.Adapter<ScheduleRecyclerAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View, textElementId: Int) : RecyclerView.ViewHolder(itemView){
        val textElement: TextView = itemView.findViewById(textElementId)
        /*val dayContentContainer: LinearLayout = itemView.findViewById(R.id.ll_certainDayItemContainer)*/
    }

    //идентификатор макета для отдельного элемента списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_certain_day, parent, false)

        itemView.apply {
            val dayContentContainer: LinearLayout = itemView.findViewById(R.id.ll_certainDayItemContainer)
            val itemCertainClassView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_certain_class, parent, false)
        }
        /*parent.addView(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_certain_class, parent, false))*/


        return MyViewHolder(itemView, textElementId)
    }

    //В методе адаптера onBindViewHolder() связываем используемые текстовые метки с данными
    // - в одном случае это значения из списка, во втором используется одна и та же строка.
    // Параметр position отвечает за позицию в списке (индекс), по которой можно получить
    // нужные данные.
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (stringsList.isNotEmpty()){
            holder.textElement.text = stringsList[position]


        }
    }

    //Возвращает количество элементов списка.
    override fun getItemCount(): Int {
        /*if (scheduleDataMap.isEmpty()){
            return itemCount
        } else {
            return scheduleDataMap.size
        }*/
        if (stringsList.isEmpty()){
            return itemCount
        } else {
            return stringsList.size
        }
    }
}