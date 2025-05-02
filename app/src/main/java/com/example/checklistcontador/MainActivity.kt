package com.example.checklistcontador

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checklistcontador.databinding.ActivityMainBinding


//Classe principal do aplicativo
class MainActivity : AppCompatActivity() {

    //criando váriavel todoAdapter e binding
    /*
    * todoAdapter:
    *
    * Binding:
    * */
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var binding: ActivityMainBinding


    //Criação das funções
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //binding vai receber uma "atividade"
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Ação que binding recebe
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //todoAcapter vai receber uma lista mutable
        //
        todoAdapter = TodoAdapter(mutableListOf())
        binding.rvTodoItems.adapter = todoAdapter
        binding.rvTodoItems.layoutManager = LinearLayoutManager(this)

        //binding ativa quando "btnAddTodo" recebe um clique
        binding.btnAddTodo.setOnClickListener {
            //recebe o texto escrito no empty
            val todoTitle = binding.etTodoTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                //cria uma checklist
                //coloca na lista
                //limpa o texto no empty
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                binding.etTodoTitle.text.clear()
            }
        }

        //binding ativa quando "btnDeleteDoneTodos" recebe um clique
        binding.btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}
