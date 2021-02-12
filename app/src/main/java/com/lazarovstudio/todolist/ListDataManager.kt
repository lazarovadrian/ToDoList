package com.lazarovstudio.todolist

import android.content.Context
import android.preference.PreferenceManager
//класс чтения и записи списков
class ListDataManager(private val context: Context) {
    fun saveList(list: TaskList){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
            sharedPreferences.putStringSet(list.name, list.tasks.toHashSet())
            sharedPreferences.apply()
    }

    fun readLists(): ArrayList<TaskList>{
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        //получает содержимое файла SharedPreferences в виде map
        //map это колекция пары обьектов - ключь и значения
        val sharedPreferenceContents = sharedPreferences.all

        //создает пустой массив для хранения списков
        val taskLists = ArrayList<TaskList>()

        //перебираем map полученый выше и преобразуем его в HashSet
        for(taskList in sharedPreferenceContents){
            val itemHashList = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, itemHashList)

            taskLists.add(list)
        }
        return taskLists
    }
}