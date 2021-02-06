package com.lazarovstudio.todolist

import android.os.Bundle
import android.text.InputType
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var listRecyclerView: RecyclerView

    val listDataManager: ListDataManager = ListDataManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            showCreateListDialog()//вызывает функцию модельного окна
        }

        val lists = listDataManager.readLists()
        listRecyclerView = findViewById<RecyclerView>(R.id.lists_recyclerview)//связываем RecyclerView с макетом по id
        listRecyclerView.layoutManager = LinearLayoutManager(this)//сообщает чтобы отобразил в макете в линейном формате элементы
        listRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists)//адаптер для получения данных и отображения и viewHolder для заполнения данных


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showCreateListDialog(){
        //создаем константы и присваиваем строки из string.xml
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)

        val builder = AlertDialog.Builder(this)//создает AlertDialog
        val listTitleEditText = EditText(this)//поле ввода
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT//указывает тип ввода,какая клавиатура для отображения
        /*
            текст = TYPE_CLASS_TEXT
            числа = TYPE_CLASS_NUMBER
            датапикер = TYPE_CLASS_DATETIME
            телефон = TYPE_CLASS_PHONE
         */
        builder.setTitle(dialogTitle)//название диалога(окна)
        builder.setView(listTitleEditText)//вид содержимого: какое поле воода будет отображаться

        builder.setPositiveButton(positiveButtonTitle){ dialog, _ ->
            val list = TaskList(listTitleEditText.text.toString())
            listDataManager.saveList(list)

            val recyclerAdapter = listRecyclerView.adapter as ListSelectionRecyclerViewAdapter
            recyclerAdapter.addList(list)

            dialog.dismiss()
        }

        builder.create().show()
    }
}