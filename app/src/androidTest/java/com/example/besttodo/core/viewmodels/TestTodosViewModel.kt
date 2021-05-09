package com.example.besttodo.core.viewmodels

import com.example.besttodo.ui.todos.TodosLoaded
import com.example.besttodo.ui.todos.TodosViewModelBase
import com.example.besttodo.ui.todos.models.UiTodo
import javax.inject.Inject

class TestTodosViewModel @Inject constructor(
    private val todosList: List<UiTodo>
) : TodosViewModelBase() {

    override fun load() {
        viewState = TodosLoaded(todosList)
    }

    override fun addTodo(todo: UiTodo) {
        // do nothing
    }

    override fun updateTodo(todo: UiTodo) {
        // do nothing
    }

    override fun deleteTodo(todo: UiTodo) {
        val list = todosList.toMutableList().apply {
            remove(todo)
        }
        viewState = TodosLoaded(list)
    }

}