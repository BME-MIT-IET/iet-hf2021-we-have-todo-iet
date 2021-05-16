package com.example.besttodo

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.besttodo.data.disk.AppDatabase
import com.example.besttodo.data.disk.TodoDao
import com.example.besttodo.data.disk.models.RoomTodo
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DataBaseTest {

    private val ROOM_TODOS = List(2) {
        RoomTodo(id = it.toLong(), name = "Name${it}")
    }

    private val ROOM_TODO0 = ROOM_TODOS[0]
    private val ROOM_TODO1= ROOM_TODOS[1]

    private lateinit var todoDao: TodoDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        todoDao = db.todoDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun testAddTodosAndGetTodos() {
        // When
        todoDao.insertTodo(ROOM_TODO0)
        todoDao.insertTodo(ROOM_TODO1)

        // Then
        val actualTodos = todoDao.getTodos()
        assertThat(actualTodos).isEqualTo(ROOM_TODOS)
    }

    @Test
    fun testUpdateAndAddAndGetTodos() {
        // When
        todoDao.insertTodo(ROOM_TODO0)
        todoDao.updateTodo(ROOM_TODO0)

        // Then
        val actualTodos = todoDao.getTodos()
        assertThat(actualTodos).hasSize(1)
    }

    @Test
    fun testDeleteAndAddAndGetTodos() {
        // When
        todoDao.insertTodo(ROOM_TODO0)
        todoDao.insertTodo(ROOM_TODO1)
        todoDao.deleteTodo(ROOM_TODO0)

        // Then
        val actualTodos = todoDao.getTodos()
        assertThat(actualTodos).doesNotContain(ROOM_TODO0)
        assertThat(actualTodos).hasSize(1)
    }

}