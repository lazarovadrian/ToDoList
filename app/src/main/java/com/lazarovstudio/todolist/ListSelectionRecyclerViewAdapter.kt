package com.lazarovstudio.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListSelectionRecyclerViewAdapter(private val lists: ArrayList<TaskList>) : RecyclerView.Adapter<ListSelectionViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        //LayoutInflater для программного создания макета
        val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_selection_view_holder, parent,false)
        return ListSelectionViewHolder(view)//возвращается ViewHolder
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        //устанавливаем значения для каждого TextView
        holder.listPosition.text = (position + 1).toString()//выводит индекс массива,на +1 и преобразует в строку
        holder.listTitle.text = lists[position].name//выводит значения массива
    }
//определяет сколько элементов в RecyclerView
    override fun getItemCount(): Int {
        return lists.size
    }

    fun addList(list:TaskList){
        lists.add(list)//обновляем TaskList
        notifyItemInserted(lists.size-1)//сообщает адаптеру что обновил список данных и обновляет RacyclerView
    }
}

