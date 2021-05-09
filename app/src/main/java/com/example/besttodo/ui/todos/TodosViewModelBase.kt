package com.example.besttodo.ui.todos

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import com.example.besttodo.ui.todos.models.UiTodo

abstract class TodosViewModelBase : RainbowCakeViewModel<TodosViewState>(Initial) {
    abstract fun load()

    abstract fun addTodo(todo: UiTodo)

    abstract fun updateTodo(todo: UiTodo)

    abstract fun deleteTodo(todo: UiTodo)
}