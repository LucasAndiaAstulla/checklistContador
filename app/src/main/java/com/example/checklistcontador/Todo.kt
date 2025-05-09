package com.example.checklistcontador

//estou criando uma classe, seus atributos são (nome do checklist e marcação de checado) no constructor primario
class Todo (val title:String, var isChecked: Boolean = false, var counter: Int = 0) {}