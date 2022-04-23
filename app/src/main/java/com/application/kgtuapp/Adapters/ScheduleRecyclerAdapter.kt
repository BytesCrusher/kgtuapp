package com.application.kgtuapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.kgtuapp.R

class CustomRecyclerAdapter(
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
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View, textElementId: Int) : RecyclerView.ViewHolder(itemView){
        val textElement: TextView = itemView.findViewById(textElementId)
    }

    //идентификатор макета для отдельного элемента списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(layoutItemId, parent, false)
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
        if (stringsList.isEmpty()){
            return itemCount
        } else {
            return stringsList.size
        }
    }
}