package com.example.checklistcontador

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Context

class TodoAdapter(private val todos: MutableList<Todo>, private val context: Context) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        val prefs = PreferenceHelper(context)

        val tvTodoTitle = holder.itemView.findViewById<TextView>(R.id.tvTodoTitle)
        val cbDone = holder.itemView.findViewById<CheckBox>(R.id.cbDone)
        val txtCounter = holder.itemView.findViewById<TextView>(R.id.txtCounter)
        val btnIncrement = holder.itemView.findViewById<Button>(R.id.btnIncrement)
        val btnDecrement = holder.itemView.findViewById<Button>(R.id.btnDecrement)

        tvTodoTitle.text = curTodo.title
        cbDone.isChecked = curTodo.isChecked

        // Carrega contador salvo
        curTodo.counter = prefs.getCounter(curTodo.title)
        txtCounter.text = curTodo.counter.toString()

        toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)

        cbDone.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(tvTodoTitle, isChecked)
            curTodo.isChecked = isChecked
        }

        btnIncrement.setOnClickListener {
            curTodo.counter += 1
            txtCounter.text = curTodo.counter.toString()
            prefs.saveCounter(curTodo.title, curTodo.counter)
        }

        btnDecrement.setOnClickListener {
            if (curTodo.counter > 0) {
                curTodo.counter -= 1
                txtCounter.text = curTodo.counter.toString()
                prefs.saveCounter(curTodo.title, curTodo.counter)
            }
        }
    }


    override fun getItemCount(): Int {
        return todos.size
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { it.isChecked }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}
