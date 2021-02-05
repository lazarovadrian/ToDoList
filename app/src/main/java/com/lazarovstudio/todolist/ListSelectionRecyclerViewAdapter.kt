package com.lazarovstudio.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListSelectionRecyclerViewAdapter : RecyclerView.Adapter<ListSelectionViewHolder>(){
    val listTitles = arrayOf("Shopping List", "Chores", "Android Tutorials")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        //LayoutInflater для программного создания макета
        val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_selection_view_holder, parent,false)
        return ListSelectionViewHolder(view)//возвращается ViewHolder
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        //устанавливаем значения для каждого TextView
        holder.listPosition.text = (position + 1).toString()//выводит индекс массива, но на +1 и преобразует в строку
        holder.listTitle.text = listTitles[position]//выводит значения массива
    }
//определяет сколько элементов в RecyclerView
    override fun getItemCount(): Int {
        return listTitles.size
    }
}

