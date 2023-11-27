package com.jeevan.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


//creating an adapter class for . data use here
//creating a view holder class to hold the layout
 class TodoAdapter(
    private val todos:MutableList<Todo>

)  : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>()
{

     class TodoViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
     {

     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.items_to_do,
                parent,
                false
                )
        )
    }

    override fun getItemCount(): Int {
        return  todos.size
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

        var curtodo=todos[position]
// Access the TextView from the ViewHolder
        var tv_todo_title = holder.itemView.findViewById<TextView>(R.id.tv_todo_title)
        var checkBox = holder.itemView.findViewById<CheckBox>(R.id.checkBox)
        // Set the text in the TextView


//        todoTitleTextView.text = curtodo.Title
//        ischecked.isChecked=curtodo.isChecked

        //below is by importing the layout file and binding with th associated paramters
        holder.itemView.apply {

            tv_todo_title.text=curtodo.Title
            checkBox.isChecked=curtodo.isChecked
            toggleStrikeThrough(tv_todo_title, curtodo.isChecked)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tv_todo_title, isChecked)
                curtodo.isChecked = !curtodo.isChecked
            }
        }
    }
}