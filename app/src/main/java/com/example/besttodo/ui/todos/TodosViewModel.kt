package com.example.besttodo.ui.todos

import android.content.Context
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import com.example.besttodo.R
import com.example.besttodo.data.ResultFailure
import com.example.besttodo.data.ResultSuccess
import com.example.besttodo.ui.todos.models.UiTodo
import com.example.besttodo.utils.ResourcesHelper
import com.example.besttodo.utils.validate
import javax.inject.Inject

class TodosViewModel @Inject constructor(
    private val todosPresenter: TodosPresenter,
    private val resourcesHelper: ResourcesHelper
) : TodosViewModelBase() {

    companion object {
        const val MAX_NUMBER_OF_TODOS = 5
    }

    override fun load() = execute {
        if(viewState !is Initial) {
            return@execute
        }
        getTodos()
    }

    override fun addTodo(todo: UiTodo) = execute {
        if(!todo.validate()) {
            postEvent(Failed(resourcesHelper.getString(R.string.error_invalid_name)))
        }
        else if(!canAddUncheckedTodo()) {
            postEvent(Failed("${resourcesHelper.getString(R.string.error_todos_limit)} $MAX_NUMBER_OF_TODOS"))
        }
        else {
            viewState = Uploading
            when(val result = todosPresenter.addTodo(todo)) {
                is ResultSuccess -> {
                    ActionSuccess(resourcesHelper.getString(R.string.txt_created))
                }
                is ResultFailure -> {
                    postEvent(Failed(result.reason))
                }
            }
            getTodos()
        }
    }

    override fun updateTodo(todo: UiTodo) = execute {
        if(!todo.checked && !canAddUncheckedTodo()) {
            postEvent(Failed("${resourcesHelper.getString(R.string.error_todos_limit)} $MAX_NUMBER_OF_TODOS"))
        }
        else {
            when(val result = todosPresenter.updateTodo(todo)) {
                is ResultFailure -> {
                    postEvent(Failed(result.reason))
                }
            }
            getTodos()
        }
    }

    override fun deleteTodo(todo: UiTodo) = execute {
        when(val result = todosPresenter.deleteTodo(todo)) {
            is ResultSuccess -> {
                postEvent(ActionSuccess(resourcesHelper.getString(R.string.txt_deleted)))
            }
            is ResultFailure -> {
                postEvent(Failed(result.reason))
            }
        }
        getTodos()
    }

    private fun canAddUncheckedTodo(): Boolean {
        val currentViewState = viewState
        return currentViewState is TodosLoaded &&
                currentViewState.todosList.count { !it.checked } < MAX_NUMBER_OF_TODOS
    }

    private suspend fun getTodos() {
        viewState = Loading
        when(val result = todosPresenter.getTodos()) {
            is ResultSuccess -> {
                viewState = TodosLoaded(result.value)
            }
            is ResultFailure -> {
                viewState = Errored
                postEvent(Failed(result.reason))
            }
        }
    }

}