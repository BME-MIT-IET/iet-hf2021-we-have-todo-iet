package com.example.besttodo.core.di

import com.example.besttodo.ui.todos.models.UiTodo
import dagger.Module
import dagger.Provides

@Module
class TestDataModule {

    companion object {
        var todosList: List<UiTodo> = listOf()
    }

    @Provides
    fun provideTodosList(): List<UiTodo> {
        return todosList
    }

}