package com.jeevan.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeevan.todolist.ui.theme.TodoListTheme


class MainActivity : ComponentActivity() {
   private lateinit var todo_adapter:TodoAdapter
    private lateinit var binding: MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn1 = findViewById<Button>(R.id.btn1)
        var btn2 = findViewById<Button>(R.id.btn2)
        var rv_list = findViewById<RecyclerView>(R.id.rv_list)
        var et_title = findViewById<EditText>(R.id.et_title)
        todo_adapter= TodoAdapter(mutableListOf())
        rv_list.adapter=todo_adapter
        rv_list.layoutManager=LinearLayoutManager(this)

        //to add toi dod list
        btn1.setOnClickListener {
            val todoTitle = et_title.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todo_adapter.addTodo(todo)
                et_title.text.clear()
            }
        }
        btn2.setOnClickListener {
            todo_adapter.deleteDoneTodos()
        }

    }
}

