package com.example.besttodo.domain

import com.example.besttodo.data.Result
import com.example.besttodo.data.disk.DiskDataSource
import com.example.besttodo.domain.models.DomainTodo
import javax.inject.Inject

class TodosInteractor @Inject constructor(
    private val diskDataSource: DiskDataSource
) {

    fun getTodos(): Result<List<DomainTodo>, String> {
        return diskDataSource.getTodos()
    }

    fun addTodo(todo: DomainTodo): Result<Unit, String> {
        return diskDataSource.addTodo(todo)
    }

    fun updateTodo(todo: DomainTodo): Result<Unit, String> {
        return diskDataSource.updateTodo(todo)
    }

    fun deleteTodo(todo: DomainTodo): Result<Unit, String> {
        return diskDataSource.deleteTodo(todo)
    }

}