package com.lazarovstudio.todolist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListSelectionViewHolder(itemView: View):
    RecyclerView.ViewHolder(itemView){
    //находим по id в макете представление и привязываем к ним данные
        val listPosition = itemView.findViewById(R.id.itemNumber) as TextView
        val listTitle = itemView.findViewById(R.id.itemString) as TextView
}