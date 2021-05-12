package com.example.besttodo

import com.example.besttodo.data.ResultFailure
import com.example.besttodo.data.ResultSuccess
import com.example.besttodo.data.disk.DiskDataSource
import com.example.besttodo.data.disk.TodoDao
import com.example.besttodo.data.disk.models.RoomTodo
import com.example.besttodo.domain.models.DomainTodo
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.mockito.kotlin.*

class DiskDataSourceTest {

    private val DOMAIN_TODOS = List(2) {
        DomainTodo(id = it.toLong(), name = "Name${it}")
    }
    private val DOMAIN_TODO0 = DOMAIN_TODOS[0]
    private val DOMAIN_TODO1= DOMAIN_TODOS[1]

    private val ROOM_TODOS = List(2) {
        RoomTodo(id = it.toLong(), name = "Name${it}")
    }

    private val ROOM_TODO0 = ROOM_TODOS[0]
    private val ROOM_TODO1= ROOM_TODOS[1]

    private val FAILURE_REASON = "Something went wrong"

    private lateinit var diskDataSource: DiskDataSource

    @Test
    fun testAddTodosSuccess() {
        // Given
        val mockTodoDao = mock<TodoDao>()
        doNothing().`when`(mockTodoDao).insertTodo(ROOM_TODO0)

        diskDataSource = DiskDataSource(mockTodoDao)

        // When
        val result = diskDataSource.addTodo(DOMAIN_TODO0)

        // Then
        assertThat(result).isEqualTo(ResultSuccess(Unit))
        verify(mockTodoDao).insertTodo(ROOM_TODO0)
    }

    @Test
    fun testAddTodosFailed() {
        // Given
        val mockTodoDao = mock<TodoDao>()
        whenever(mockTodoDao.insertTodo(ROOM_TODO0)).doThrow(RuntimeException(FAILURE_REASON))

        diskDataSource = DiskDataSource(mockTodoDao)

        // When
        val result = diskDataSource.addTodo(DOMAIN_TODO0)

        // Then
        assertThat(result).isEqualTo(ResultFailure(FAILURE_REASON))
        verify(mockTodoDao).insertTodo(ROOM_TODO0)
    }

    @Test
    fun testGetTodosSuccess() {
        // Given
        val mockTodoDao = mock<TodoDao>()
        whenever(mockTodoDao.getTodos()) doReturn ROOM_TODOS

        diskDataSource = DiskDataSource(mockTodoDao)

        // When
        val result = diskDataSource.getTodos()

        // Then
        assertThat(result).isEqualTo(ResultSuccess(DOMAIN_TODOS))
        verify(mockTodoDao).getTodos()
    }

    @Test
    fun testGetTodosFailed() {
        // Given
        val mockTodoDao = mock<TodoDao>()
        whenever(mockTodoDao.getTodos()).doThrow(RuntimeException(FAILURE_REASON))

        diskDataSource = DiskDataSource(mockTodoDao)

        // When
        val result = diskDataSource.getTodos()

        // Then
        assertThat(result).isEqualTo(ResultFailure(FAILURE_REASON))
        verify(mockTodoDao).getTodos()
    }

    @Test
    fun testUpdateTodosSuccess() {
        // Given
        val mockTodoDao = mock<TodoDao>()
        doNothing().`when`(mockTodoDao).updateTodo(ROOM_TODO0)

        diskDataSource = DiskDataSource(mockTodoDao)

        // When
        val result = diskDataSource.updateTodo(DOMAIN_TODO0)

        // Then
        assertThat(result).isEqualTo(ResultSuccess(Unit))
        verify(mockTodoDao).updateTodo(ROOM_TODO0)
    }

    @Test
    fun testUpdateTodosFailed() {
        // Given
        val mockTodoDao = mock<TodoDao>()
        whenever(mockTodoDao.updateTodo(ROOM_TODO0)).doThrow(RuntimeException(FAILURE_REASON))

        diskDataSource = DiskDataSource(mockTodoDao)

        // When
        val result = diskDataSource.updateTodo(DOMAIN_TODO0)

        // Then
        assertThat(result).isEqualTo(ResultFailure(FAILURE_REASON))
        verify(mockTodoDao).updateTodo(ROOM_TODO0)
    }

    @Test
    fun testDeleteTodosSuccess() {
        // Given
        val mockTodoDao = mock<TodoDao>()
        doNothing().`when`(mockTodoDao).deleteTodo(ROOM_TODO0)

        diskDataSource = DiskDataSource(mockTodoDao)

        // When
        val result = diskDataSource.deleteTodo(DOMAIN_TODO0)

        // Then
        assertThat(result).isEqualTo(ResultSuccess(Unit))
        verify(mockTodoDao).deleteTodo(ROOM_TODO0)
    }

    @Test
    fun testDeleteTodosFailed() {
        // Given
        val mockTodoDao = mock<TodoDao>()
        whenever(mockTodoDao.deleteTodo(ROOM_TODO0)).doThrow(RuntimeException(FAILURE_REASON))

        diskDataSource = DiskDataSource(mockTodoDao)

        // When
        val result = diskDataSource.deleteTodo(DOMAIN_TODO0)

        // Then
        assertThat(result).isEqualTo(ResultFailure(FAILURE_REASON))
        verify(mockTodoDao).deleteTodo(ROOM_TODO0)
    }


}